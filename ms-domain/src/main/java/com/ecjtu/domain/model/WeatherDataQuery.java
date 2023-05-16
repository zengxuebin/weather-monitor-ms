package com.ecjtu.domain.model;

import lombok.Data;

/**
 * @Description: 气象数据条件
 * @Author: ZengXueBin
 * @Date: 2023/5/16 14:32
 */
@Data
public class WeatherDataQuery {

    private Long stationNo;
    private Integer windDirection;

}
