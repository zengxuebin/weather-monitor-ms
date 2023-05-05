package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.AlertRule;
import com.ecjtu.mapper.AlertRuleMapper;
import com.ecjtu.service.AlertRuleService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警规则 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:10
 */
@Service
public class AlertRuleServiceImpl extends ServiceImpl<AlertRuleMapper, AlertRule> implements AlertRuleService {
}
