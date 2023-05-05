package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.AlertPush;
import com.ecjtu.mapper.AlertPushMapper;
import com.ecjtu.service.AlertPushService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警推送 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:08
 */
@Service
public class AlertPushServiceImpl extends ServiceImpl<AlertPushMapper, AlertPush> implements AlertPushService {
}
