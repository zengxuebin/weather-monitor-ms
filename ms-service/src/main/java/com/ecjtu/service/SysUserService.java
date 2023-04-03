package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.SysUser;

import java.util.List;

/**
 * @Description: 用户 业务层
 * @Author: ZengXueBin
 * @Date: 2023/3/31 20:25
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据条件分页查询用户列表
     * @param user 用户信息
     * @return 用户信息集合
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 根据条件分页查询已分配用户角色列表
     * @param user 用户信息
     * @return 已分配角色用户信息集合
     */
    List<SysUser> selectAllocateList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * @param user 用户信息
     * @return 未分配角色用户信息集合
     */
    List<SysUser> selectUnallocateList(SysUser user);

    /**
     * 检验用户名是否唯一
     * @param user 用户
     * @return true=唯一 false=不唯一
     */
    boolean checkUsernameUnique(SysUser user);

    /**
     * 校验手机号是否唯一
     * @param user 用户
     * @return true=唯一 false=不唯一
     */
    boolean checkPhoneUnique(SysUser user);

    /**
     * 校验邮箱是否唯一
     * @param user 用户
     * @return true=唯一 false=不唯一
     */
    boolean checkEmailUnique(SysUser user);

    /**
     * 新增用户信息
     * @param user 用户
     * @return 1=成功 0=失败
     */
    int insertUser(SysUser user);

    /**
     * 政策用户
     * @param user 用户
     * @return true=成功 false=失败
     */
    boolean registerUser(SysUser user);

    /**
     * 修改用户信息
     * @param user 用户
     * @return 1=修改成功 0=修改失败
     */
    int updateUser(SysUser user);

    /**
     * 用户授权角色
     * @param userId 用户id
     * @param roleIds 角色组
     */
    void authorizeUser(Long userId, Long[] roleIds);

    /**
     * 修改用户头像
     * @param username 用户名
     * @param avatar 头像地址
     * @return true=修改成功 false=修改失败
     */
    boolean updateUserAvatar(String username, String avatar);

    /**
     * 重置用户密码
     * @param user 用户
     * @return true=重置成功 false=重置失败
     */
    int resetPassword(SysUser user);

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     * @return true=删除成功 false=删除失败
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * @param userIds 用户id
     * @return true=删除成功 false=删除失败
     */
    int deleteUserByIds(Long[] userIds);
}
