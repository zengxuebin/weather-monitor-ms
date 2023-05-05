package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.WeatherData;
import com.ecjtu.mapper.WeatherDataMapper;
import com.ecjtu.service.WeatherDataService;
import org.springframework.stereotype.Service;

/**
 * @Description: 气象数据 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:13
 */
@Service
public class WeatherDataServiceImpl extends ServiceImpl<WeatherDataMapper, WeatherData> implements WeatherDataService {
}
