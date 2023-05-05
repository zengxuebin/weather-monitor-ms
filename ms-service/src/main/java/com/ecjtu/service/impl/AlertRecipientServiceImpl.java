package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.domain.entity.AlertRecipient;
import com.ecjtu.mapper.AlertRecipientMapper;
import com.ecjtu.service.AlertRecipientService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警接收 业务实现层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:09
 */
@Service
public class AlertRecipientServiceImpl extends ServiceImpl<AlertRecipientMapper, AlertRecipient> implements AlertRecipientService {
}
