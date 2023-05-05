package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.WeatherAlert;
import com.ecjtu.mapper.WeatherAlertMapper;
import com.ecjtu.service.WeatherAlertService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警信息 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:12
 */
@Service
public class WeatherAlertServiceImpl extends ServiceImpl<WeatherAlertMapper, WeatherAlert> implements WeatherAlertService {
}
