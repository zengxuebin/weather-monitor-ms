package com.ecjtu.domain.VO;

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
    private String rolePerm;
    private Integer orderNum;
    private String status;
    private String delFlag;
    private Date createTime;
    private String remark;
}
