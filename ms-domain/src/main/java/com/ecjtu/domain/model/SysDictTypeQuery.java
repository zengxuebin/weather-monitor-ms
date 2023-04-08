package com.ecjtu.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 字典类型分页查询条件
 * @Author: ZengXueBin
 * @Date: 2023/4/8 14:21
 */
@Setter
@Getter
public class SysDictTypeQuery {

    private String dictName;
    private String status;
    private String dictType;
    private String beginTime;
    private String endTime;

}
