package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.WeatherStation;

import java.util.List;
import java.util.Map;

/**
 * @Description: 气象站台 业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:06
 */
public interface WeatherStationService extends IService<WeatherStation> {

    /**
     * 获取所有城市列表
     * @return 城市列表
     */
    List<Map<String, Object>> getAllCity();

}
