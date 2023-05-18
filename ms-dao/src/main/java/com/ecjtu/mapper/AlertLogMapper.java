package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.AlertLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警日志 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:52
 */
@Mapper
public interface AlertLogMapper extends BaseMapper<AlertLog> {

}
