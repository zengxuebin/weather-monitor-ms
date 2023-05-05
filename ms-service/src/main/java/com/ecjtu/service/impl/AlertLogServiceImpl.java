package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.AlertLog;
import com.ecjtu.mapper.AlertLogMapper;
import com.ecjtu.service.AlertLogService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警记录 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:07
 */
@Service
public class AlertLogServiceImpl extends ServiceImpl<AlertLogMapper, AlertLog> implements AlertLogService {
}
