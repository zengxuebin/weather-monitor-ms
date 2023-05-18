package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.WeatherData;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description: 气象数据 业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:05
 */
public interface WeatherDataService extends IService<WeatherData> {
    /**
     * 采集数据保存至数据库
     * @param stationNo 站点号
     * @param json 气象数据
     */
    void saveWeatherDataToDatabase(Long stationNo, String json);

    /**
     * 获取今日最新的气象数据
     * @param today 今日日期
     * @return 气象数据
     */
    List<WeatherData> getLatestDataForEachStation(LocalDate today);
}
