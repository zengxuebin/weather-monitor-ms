package com.ecjtu.domain.model;

import lombok.Data;
import lombok.Setter;

/**
 * @Description: 气象数据条件
 * @Author: ZengXueBin
 * @Date: 2023/5/16 14:32
 */
@Setter
@Data
public class WeatherDataQuery {

    private Long stationNo;
    private Integer windDirection;

}
