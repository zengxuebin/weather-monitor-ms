package com.ecjtu.domain.DTO;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 温差
 * @Author: ZengXueBin
 * @Date: 2023/5/15 11:26
 */
@Data
public class TempRangeDTO {

    @JSONField(format = "yyyy-MM-dd")
    private Date historyDate;
    private Float maxTemp;
    private Float minTemp;
}
