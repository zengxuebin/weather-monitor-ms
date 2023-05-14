package com.ecjtu.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 菜单查询条件
 * @Author: ZengXueBin
 * @Date: 2023/5/14 21:10
 */
@Getter
@Setter
public class SysMenuQuery {

    private String menuName;
    private String menuType;
    private String visible;
}
