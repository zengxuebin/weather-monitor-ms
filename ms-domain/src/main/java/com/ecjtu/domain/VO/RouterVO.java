package com.ecjtu.domain.VO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: 路由配置信息
 * @Author: ZengXueBin
 * @Date: 2023/4/8 23:11
 */
@Getter
@Setter
public class RouterVO {

    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 是否隐藏路由 true时路由不会在侧边栏出现
     */
    private boolean hidden;
    /**
     * 重定向地址 当设置noRedirect的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 其他元素
     */
    private MetaVO meta;
    /**
     * 子路由
     */
    private List<RouterVO> children;

}
