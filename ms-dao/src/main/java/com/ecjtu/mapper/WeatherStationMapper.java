package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.DTO.StationCountDTO;
import com.ecjtu.domain.DTO.StationTypeCountDTO;
import com.ecjtu.domain.entity.WeatherStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 气象站 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 04:00
 */
@Mapper
public interface WeatherStationMapper extends BaseMapper<WeatherStation> {
    /**
     * 获取监测站个数
     * @return 监测站个数
     */
    List<StationCountDTO> getCountStation();

    /**
     * 统计监测站类型
     * @return 类型个数
     */
    List<StationTypeCountDTO> getCountStationType();
}
