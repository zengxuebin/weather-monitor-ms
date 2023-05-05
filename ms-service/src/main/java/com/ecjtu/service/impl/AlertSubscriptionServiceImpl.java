package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.AlertSubscription;
import com.ecjtu.mapper.AlertSubscriptionMapper;
import com.ecjtu.service.AlertSubscriptionService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警订阅 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:11
 */
@Service
public class AlertSubscriptionServiceImpl extends ServiceImpl<AlertSubscriptionMapper, AlertSubscription> implements AlertSubscriptionService {
}
