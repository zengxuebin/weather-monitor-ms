package com.ecjtu.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 路由显示信息
 * @Author: ZengXueBin
 * @Date: 2023/4/9 08:29
 */
@AllArgsConstructor
@Getter
@Setter
public class MetaVO {
    /**
     * 路由在侧边栏的标题
     */
    private String title;
    /**
     * 设置路由的图标
     */
    private String icon;
}
