package com.ecjtu.web.controller.weather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.WeatherData;
import com.ecjtu.domain.entity.WeatherStation;
import com.ecjtu.domain.model.WeatherDataQuery;
import com.ecjtu.service.WeatherDataService;
import com.ecjtu.service.WeatherStationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description: 数据采集
 * @Author: ZengXueBin
 * @Date: 2023/5/16 12:10
 */
@RestController
@RequestMapping("/weatherData")
public class WeatherDataController {

    @Autowired
    private WeatherStationService stationService;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 采集气象数据
     * @return 气象数据列表
     */
    @PostMapping("/collect")
    public ApiResult collectWeatherData() {
        List<WeatherStation> stationList = stationService.list();
        RestTemplate restTemplate = new RestTemplate();
        String json = "";
        for (WeatherStation station : stationList) {
            try {
                String location = station.getStationLongitude() + "," + station.getStationLatitude();
                json = restTemplate.getForObject("https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/" + location + "/realtime",
                        String.class);
                weatherDataService.saveWeatherDataToDatabase(station.getStationNo(), json);
            } catch (Exception ex) {
                // 处理429错误
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ApiResult.success(stationList);
    }

    /**
     * 分页查询气象数据
     * @param query 查询条件
     * @return 监测站
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherDataQuery> query) {
        LambdaQueryWrapper<WeatherData> queryWrapper = new LambdaQueryWrapper<>();
        WeatherDataQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (ObjectUtils.isNotEmpty(entity.getStationNo())) {
                queryWrapper.eq(WeatherData::getStationNo, entity.getStationNo());
            }
            if (ObjectUtils.isNotEmpty(entity.getWindDirection())) {
                queryWrapper.eq(WeatherData::getWindDirection, entity.getWindDirection());
            }
        }

        queryWrapper.orderByDesc(WeatherData::getDataCollectTime);
        Page<WeatherData> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(weatherDataService.page(page, queryWrapper));
    }
}
