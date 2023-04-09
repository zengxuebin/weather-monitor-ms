package com.ecjtu.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description: 用户分页查询条件
 * @Author: ZengXueBin
 * @Date: 2023/4/9 16:34
 */
@Getter
@Setter
public class SysUserQuery {

    private String username;
    private String phone;
    private String status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
