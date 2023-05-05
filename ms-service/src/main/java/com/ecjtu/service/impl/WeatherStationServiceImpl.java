package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.WeatherStation;
import com.ecjtu.mapper.WeatherStationMapper;
import com.ecjtu.service.WeatherStationService;
import org.springframework.stereotype.Service;

/**
 * @Description: 气象站台 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:14
 */
@Service
public class WeatherStationServiceImpl extends ServiceImpl<WeatherStationMapper, WeatherStation> implements WeatherStationService {
}
