package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.AlertSubscription;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警订阅 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:57
 */
@Mapper
public interface AlertSubscriptionMapper extends BaseMapper<AlertSubscription> {
}
