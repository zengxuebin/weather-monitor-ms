package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.WeatherData;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description: 气象数据 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:58
 */
@Mapper
public interface WeatherDataMapper extends BaseMapper<WeatherData> {
    List<WeatherData> getLatestDataForEachStation(LocalDate today);
}
