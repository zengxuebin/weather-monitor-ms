package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.WeatherStation;
import com.ecjtu.mapper.WeatherStationMapper;
import com.ecjtu.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 气象站台 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:14
 */
@Service
public class WeatherStationServiceImpl extends ServiceImpl<WeatherStationMapper, WeatherStation> implements WeatherStationService {

    @Autowired
    private WeatherStationMapper stationMapper;

    /**
     * 获取所有城市列表
     *
     * @return 城市列表
     */
    @Override
    public List<Map<String, Object>> getAllCity() {
        QueryWrapper<WeatherStation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT station_city");
        return stationMapper.selectMaps(queryWrapper);
    }

}
