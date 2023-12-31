package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.AlertPush;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警推送 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:55
 */
@Mapper
public interface AlertPushMapper extends BaseMapper<AlertPush> {
}
