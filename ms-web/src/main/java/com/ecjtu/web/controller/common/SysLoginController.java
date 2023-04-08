package com.ecjtu.web.controller.common;

import com.ecjtu.service.SysMenuService;
import com.ecjtu.web.service.SysLoginService;
import com.ecjtu.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 登陆验证
 * @Author: ZengXueBin
 * @Date: 2023/4/8 18:09
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

//    /**
//     * 账号密码登陆
//     * @param loginBody 登陆信息
//     * @return 结果
//     */
//    public ApiResult login(@RequestBody LoginBody loginBody) {
//
//    }
}
