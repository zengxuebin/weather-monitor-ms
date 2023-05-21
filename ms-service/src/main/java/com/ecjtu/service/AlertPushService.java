package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.AlertPush;
import com.ecjtu.domain.entity.SysUser;

import java.util.List;

/**
 * @Description: 预警推送 业务层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:02
 */
public interface AlertPushService extends IService<AlertPush> {

    /**
     * 推送预警信息
     *
     * @param alertIds 预警信息ID
     * @return 结果
     */
    List<SysUser> pushAlert(List<Long> alertIds);
}
