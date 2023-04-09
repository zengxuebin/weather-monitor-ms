package com.ecjtu.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
