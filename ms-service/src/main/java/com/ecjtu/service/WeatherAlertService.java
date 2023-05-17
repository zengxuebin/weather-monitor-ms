package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.WeatherAlert;
import com.ecjtu.domain.entity.WeatherData;

import java.util.List;

/**
 * @Description: 预警信息 业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:04
 */
public interface WeatherAlertService extends IService<WeatherAlert> {
    /**
     * 生成预警信息
     * @param weatherDataList 气象数据列表
     */
    void processWeatherData(List<WeatherData> weatherDataList);
}
