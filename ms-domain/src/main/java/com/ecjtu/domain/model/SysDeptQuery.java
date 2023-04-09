package com.ecjtu.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 部门分页查询条件
 * @Author: ZengXueBin
 * @Date: 2023/4/9 09:43
 */
@Getter
@Setter
public class SysDeptQuery {

    private String deptName;
    private String leader;
    private String status;

}
