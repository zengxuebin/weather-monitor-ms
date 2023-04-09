package com.ecjtu.web.controller.common;

import com.ecjtu.common.constant.Constants;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.entity.SysMenu;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.domain.model.LoginBody;
import com.ecjtu.service.SysMenuService;
import com.ecjtu.web.service.SysLoginService;
import com.ecjtu.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Description: 登陆验证
 * @Author: ZengXueBin
 * @Date: 2023/4/8 18:09
 */
@RestController
@RequestMapping("/auth")
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 账号密码登陆
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(),
                loginBody.getCode(), loginBody.getUuid());
        ApiResult apiResult = ApiResult.success();
        apiResult.put(Constants.TOKEN, token);
        return apiResult;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("/getUserInfo")
    public ApiResult getUserInfo() {
        SysUser user = SecurityUtil.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        ApiResult apiResult = ApiResult.success();
        apiResult.put("user", user);
        apiResult.put("roles", roles);
        apiResult.put("permissions", permissions);
        return apiResult;
    }

    /**
     * 获取路由信息
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ApiResult getRouters() {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return ApiResult.success(menuService.buildMenus(menus));
    }
}
