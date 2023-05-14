package com.ecjtu.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 监测站分页查询条件
 * @Author: ZengXueBin
 * @Date: 2023/5/14 14:13
 */
@Setter
@Getter
public class WeatherStationQuery {

    private String stationProvince;
    private String stationCity;
    private String stationType;

}
