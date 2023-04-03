package com.ecjtu.domain.VO;

import lombok.Data;

import java.util.Date;

/**
 * @Description: menu VO
 * @Author: ZengXueBin
 * @Date: 2023/4/2 21:47
 */
//select menu_id, menu_name, parent_id, order_num, path, component,
// `query`, is_frame, is_cache, menu_type, visible, status, ifnull(perms,'') as perms,
// icon, create_time
@Data
public class SysMenuVO {
    private Long menuId;
    private String menuName;
    private String parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private String menu_type;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    private Date createTime;
}
