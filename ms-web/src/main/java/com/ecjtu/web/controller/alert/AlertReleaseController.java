package com.ecjtu.web.controller.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.WeatherAlert;
import com.ecjtu.domain.entity.WeatherData;
import com.ecjtu.domain.model.WeatherAlertQuery;
import com.ecjtu.service.WeatherAlertService;
import com.ecjtu.service.WeatherDataService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 预警发布
 * @Author: ZengXueBin
 * @Date: 2023/5/17 07:40
 */
@RestController
@RequestMapping("/alert/release")
public class AlertReleaseController {

    @Autowired
    private WeatherAlertService alertService;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 查询预警信息（status = '0'）
     * @param query 查询条件
     * @return 结果集
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherAlertQuery> query) {
        LambdaQueryWrapper<WeatherAlert> queryWrapper = new LambdaQueryWrapper<>();
        WeatherAlertQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getAlertTitle())) {
                queryWrapper.like(WeatherAlert::getAlertTitle, entity.getAlertTitle());
            }
            if (StringUtils.isNotBlank(entity.getAlertType())) {
                queryWrapper.eq(WeatherAlert::getAlertType, entity.getAlertType());
            }
            if (StringUtils.isNotBlank(entity.getAlertLevel())) {
                queryWrapper.eq(WeatherAlert::getAlertLevel, entity.getAlertLevel());
            }
            if (StringUtils.isNotBlank(entity.getAlertRuleId())) {
                queryWrapper.eq(WeatherAlert::getAlertRuleId, entity.getAlertRuleId());
            }
            if (StringUtils.isNotBlank(entity.getAlertSource())) {
                queryWrapper.eq(WeatherAlert::getAlertSource, entity.getAlertSource());
            }
            if (StringUtils.isNotBlank(entity.getAlertAreaId())) {
                queryWrapper.eq(WeatherAlert::getAlertAreaId, entity.getAlertAreaId());
            }
            queryWrapper.eq(WeatherAlert::getAlertStatus, '0');
            queryWrapper.orderByDesc(WeatherAlert::getTriggerTime);
        }

        Page<WeatherAlert> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(alertService.page(page, queryWrapper));
    }

    /**
     * 生成预警信息保存在数据库中
     * @return 结果
     */
    @PostMapping("/generate")
    public ApiResult generateWeatherAlert() {
        LocalDate today = LocalDate.now();

        // 气象数据
        LambdaQueryWrapper<WeatherData> dataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dataLambdaQueryWrapper.eq(WeatherData::getIsHandled, "1");
        dataLambdaQueryWrapper.apply("DATE(data_collect_time) = DATE({0})", today);
        List<WeatherData> weatherDataList = weatherDataService.list(dataLambdaQueryWrapper);
        for (WeatherData weatherData : weatherDataList) {
            System.out.println(weatherData);
        }

        alertService.processWeatherData(weatherDataList);

        // 标记为已处理
        ArrayList<WeatherData> updatedList = new ArrayList<>();
        for (WeatherData weatherData : weatherDataList) {
            System.out.println(weatherData);
        }
        for (WeatherData weatherData : weatherDataList) {
            weatherData.setIsHandled("0");
            updatedList.add(weatherData);
        }
        weatherDataService.updateBatchById(updatedList);

        return ApiResult.success(weatherDataList);
    }

}
