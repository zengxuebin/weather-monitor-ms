package com.ecjtu.domain.VO;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description: role VO
 * @Author: ZengXueBin
 * @Date: 2023/4/2 21:15
 */
@Data
public class SysRoleVO {

    private String roleId;
    private String roleName;
    private String roleKey;
    private Integer orderNum;
    private String status;
    private String delFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String remark;
}
