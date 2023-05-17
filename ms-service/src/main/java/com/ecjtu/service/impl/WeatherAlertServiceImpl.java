package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.AlertResultConstants;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.entity.AlertLog;
import com.ecjtu.domain.entity.AlertRule;
import com.ecjtu.domain.entity.WeatherAlert;
import com.ecjtu.domain.entity.WeatherData;
import com.ecjtu.mapper.AlertLogMapper;
import com.ecjtu.mapper.AlertRuleMapper;
import com.ecjtu.mapper.WeatherAlertMapper;
import com.ecjtu.service.WeatherAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 预警信息 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:12
 */
@Service
public class WeatherAlertServiceImpl extends ServiceImpl<WeatherAlertMapper, WeatherAlert> implements WeatherAlertService {

    @Autowired
    private AlertRuleMapper alertRuleMapper;

    @Autowired
    private WeatherAlertMapper weatherAlertMapper;

    @Autowired
    private AlertLogMapper alertLogMapper;


    /**
     * 生成预警信息
     *
     * @param weatherDataList 气象数据列表
     */
    @Override
    public void processWeatherData(List<WeatherData> weatherDataList) {
        // 预警规则
        LambdaQueryWrapper<AlertRule> ruleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ruleLambdaQueryWrapper.eq(AlertRule::getAlertStatus, "0");
        List<AlertRule> alertRuleList = alertRuleMapper.selectList(ruleLambdaQueryWrapper);

        // 按照预警类型分组并排序
        Map<String, List<AlertRule>> alertRuleGroups = alertRuleList.stream()
                .collect(Collectors.groupingBy(AlertRule::getMetric));

        // 标记是否已生成预警
        boolean generatedAlert = false;

        for (WeatherData weatherData : weatherDataList) {
            // 遍历每个预警类型的规则组
            for (Map.Entry<String, List<AlertRule>> entry : alertRuleGroups.entrySet()) {
                List<AlertRule> ruleGroup = entry.getValue();

                // 按照优先级升序排列
                ruleGroup.sort(Comparator.comparingInt(AlertRule::getPriority));

                // 遍历规则组内的规则
                for (AlertRule alertRule : ruleGroup) {
                    if (isConditionSatisfied(weatherData, alertRule)) {// 生成预警
                        if (!generatedAlert) {
                            WeatherAlert weatherAlert = generateAlertInfo(weatherData, alertRule);
                            weatherAlertMapper.insert(weatherAlert);
                            // 记录日志
                            recordAlertLog(weatherAlert, AlertResultConstants.SYSTEM_TRIGGERED);
                            generatedAlert = true; // 标记已生成预警
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断是否满足预警生成的条件
     *
     * @param weatherData 气象数据
     * @param alertRule   预警规则
     * @return true=满足 false=不满足
     */
    private boolean isConditionSatisfied(WeatherData weatherData, AlertRule alertRule) {
        String metric = alertRule.getMetric();
        Float threshold = alertRule.getThreshold();
        String operator = alertRule.getOperator();

        Object metricValueObj = getMetricValue(weatherData, metric);

        Float metricValue = null;
        if (metricValueObj instanceof Float) {
            metricValue = (Float) metricValueObj;
        } else if (metricValueObj instanceof Integer) {
            metricValue = ((Integer) metricValueObj).floatValue();
        } else {
            metricValue = 0.0f;
        }

        // 判断是否符合
        boolean isConditionSatisfied = false;

        switch (operator) {
            case ">" -> isConditionSatisfied = metricValue > threshold;
            case ">=" -> isConditionSatisfied = metricValue >= threshold;
            case "<" -> isConditionSatisfied = metricValue < threshold;
            case "<=" -> isConditionSatisfied = metricValue <= threshold;
            case "=" -> isConditionSatisfied = Objects.equals(metricValue, threshold);
            default -> {
            }
        }
        return isConditionSatisfied;
    }

    /**
     * 获取气象数据指标的值
     *
     * @param weatherData 气象数据
     * @param metric      指标
     * @return 指标的值
     */
    private Object getMetricValue(WeatherData weatherData, String metric) {
        Object value = null;
        try {
            Field field = WeatherData.class.getDeclaredField(metric);
            field.setAccessible(true);
            value = field.get(weatherData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * 生成预警信息
     *
     * @param weatherData 气象数据
     * @param alertRule   预警规则
     * @return 预警信息
     */
    private WeatherAlert generateAlertInfo(WeatherData weatherData, AlertRule alertRule) {
        WeatherAlert weatherAlert = new WeatherAlert();
        String[] parts = alertRule.getRuleName().split("-");
        String alertTitle = parts[1] + parts[0];
        weatherAlert.setAlertTitle(alertTitle);

        String metric = alertRule.getMetric();
        Object metricValueObj = getMetricValue(weatherData, metric);
        Float metricValue = null;
        if (metricValueObj instanceof Float) {
            metricValue = (Float) metricValueObj;
        } else if (metricValueObj instanceof Integer) {
            metricValue = ((Integer) metricValueObj).floatValue();
        } else {
            metricValue = 0.0f;
        }

        weatherAlert.setTriggerValue(metricValue);
        weatherAlert.setAlertRuleId(alertRule.getRuleId());
        weatherAlert.setAlertLevel(parts[1]);
        weatherAlert.setAlertAreaId(weatherData.getStationNo());
        weatherAlert.setAlertType("天气预警");
        weatherAlert.setTriggerTime(new Date());
        weatherAlert.setStartTime(LocalDateTime.now());
        weatherAlert.setEndTime(LocalDateTime.now().plusHours(2));
        weatherAlert.setAlertStatus("0");
        return weatherAlert;
    }

    /**
     * 处理预警日志的方法
     *
     * @param weatherAlert 预警
     */
    public void recordAlertLog(WeatherAlert weatherAlert, String handleResult) {
        String username = SecurityUtil.getUsername();
        AlertLog alertLog = new AlertLog();
        alertLog.setAlertId(weatherAlert.getAlertId());
        alertLog.setHandlerUser(username);
        alertLog.setHandleTime(new Date());
        alertLog.setHandleResult(handleResult);
        alertLogMapper.insert(alertLog);
    }
}
