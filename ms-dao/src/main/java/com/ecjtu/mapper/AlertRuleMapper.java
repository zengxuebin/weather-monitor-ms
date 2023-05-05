package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.AlertRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警规则 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:56
 */
@Mapper
public interface AlertRuleMapper extends BaseMapper<AlertRule> {
}
