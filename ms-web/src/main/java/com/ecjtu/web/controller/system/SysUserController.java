package com.ecjtu.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.constant.UserConstants;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.RedisCache;
import com.ecjtu.common.utils.SecurityUtil;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.domain.model.SysUserQuery;
import com.ecjtu.service.SysDeptService;
import com.ecjtu.service.SysRoleService;
import com.ecjtu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 用户信息
 * @Author: ZengXueBin
 * @Date: 2023/4/9 15:35
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysDeptService deptService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 分页查询用户
     * @param pageInfo 分页条件
     * @return 数据
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysUserQuery> pageInfo) {
        System.out.println(pageInfo.getPageNum());
        IPage<SysUser> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ApiResult.success(userService.page(page));
    }

    /**
     * 根据用户编号获取详情信息
     * @param userId 用户编号
     * @return 详情
     */
    @GetMapping("/{userId}")
    public ApiResult getUserInfo(@PathVariable Long userId) {
        return ApiResult.success(userService.getById(userId));
    }

    /**
     * 新增用户
     * @param user 用户
     * @return 结果
     */
    @PostMapping("/save")
    public ApiResult saveUser(@Validated @RequestBody SysUser user) {
        if (!userService.checkUsernameUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，登陆账号已存在");
        } else if (!userService.checkPhoneUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，手机号码已存在");
        } else if (!userService.checkEmailUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，电子邮箱已存在");
        }
        user.setCreateBy(SecurityUtil.getLoginUser().getUsername());
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        return userService.save(user) ? ApiResult.success("创建成功") : ApiResult.error("创建失败，请联系系统管理员");
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/edit")
    public ApiResult editUser(@Validated @RequestBody SysUser user) {
        if (!userService.checkUsernameUnique(user)) {
            return ApiResult.warn("修改用户'" + user.getUsername() + "'失败，登陆账号已存在");
        } else if (!userService.checkPhoneUnique(user)) {
            return ApiResult.warn("修改用户'" + user.getUsername() + "'失败，手机号码已存在");
        } else if (!userService.checkEmailUnique(user)) {
            return ApiResult.warn("修改用户'" + user.getUsername() + "'失败，电子邮箱已存在");
        }
        user.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        return userService.updateById(user) ? ApiResult.success("修改成功") : ApiResult.error("修改失败，请联系系统管理员");
    }

    /**
     * 修改用户状态
     * @param user 用户
     * @return 修改结果
     */
    @PostMapping("/changeStatus")
    public ApiResult changeStatus(@RequestBody SysUser user) {
        user.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        return userService.updateById(user) ? ApiResult.success("状态修改成功") : ApiResult.error("状态修改失败，请联系系统管理员");
    }

    /**
     * 删除用户
     * @param userIds 用户id
     * @return 结果
     */
    @PostMapping("/del")
    public ApiResult removeUserByIds(List<Long> userIds) {
        return userService.removeByIds(userIds) ? ApiResult.success("删除成功") : ApiResult.error("删除失败");
    }

    /**
     * 获取在线用户列表
     * @return 在线用户
     */
    @PostMapping("/online/page")
    public ApiResult userOnline(@RequestBody PageInfo<SysUserQuery> query) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getOnline, UserConstants.ONLINE);
        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(userService.page(page, queryWrapper));
    }
}
