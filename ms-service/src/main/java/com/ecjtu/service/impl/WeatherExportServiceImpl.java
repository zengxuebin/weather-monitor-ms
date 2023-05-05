package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.WeatherExport;
import com.ecjtu.mapper.WeatherExportMapper;
import com.ecjtu.service.WeatherExportService;
import org.springframework.stereotype.Service;

/**
 * @Description: 气象数据导出 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:13
 */
@Service
public class WeatherExportServiceImpl extends ServiceImpl<WeatherExportMapper, WeatherExport> implements WeatherExportService {
}
