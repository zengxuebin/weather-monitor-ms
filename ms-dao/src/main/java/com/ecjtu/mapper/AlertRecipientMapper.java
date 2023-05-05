package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.AlertRecipient;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警接收 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:56
 */
@Mapper
public interface AlertRecipientMapper extends BaseMapper<AlertRecipient> {
}
