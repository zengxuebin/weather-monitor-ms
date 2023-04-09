package com.ecjtu.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: Entity基类
 * @Author: ZengXueBin
 * @Date: 2023/3/28 09:50
 */
@Setter
@Getter
public class BaseEntity implements Serializable {

    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
}
