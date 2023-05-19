package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.WeatherAlert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 预警信息 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:58
 */
@Mapper
public interface WeatherAlertMapper extends BaseMapper<WeatherAlert> {
    /**
     * 修改状态
     * @param alertIdsString 预警信息ids
     * @param status 修改后的状态
     */
    void updateStatusByIds(@Param("alertIdsString") String alertIdsString,
                           @Param("status") String status);
}
