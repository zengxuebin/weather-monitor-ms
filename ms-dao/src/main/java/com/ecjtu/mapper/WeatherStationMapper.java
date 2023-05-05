package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.WeatherStation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 气象站 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:00
 */
@Mapper
public interface WeatherStationMapper extends BaseMapper<WeatherStation> {
}
