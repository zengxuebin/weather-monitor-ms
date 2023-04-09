package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.UserConstants;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.mapper.SysUserMapper;
import com.ecjtu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户 业务层实现
 * @Author: ZengXueBin
 * @Date: 2023/3/31 20:46
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 检验用户名是否唯一
     * @param user 用户
     * @return true=唯一 false=不唯一
     */
    @Override
    public boolean checkUsernameUnique(SysUser user) {
        long userID = user.getUserId() == null ? -1L : user.getUserId();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, user.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null && sysUser.getUserId() != userID) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 检验手机号是否唯一
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public boolean checkPhoneUnique(SysUser user) {
        long userId = user.getUserId() == null ? -1L : user.getUserId();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getPhone, user.getPhone());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser != null && sysUser.getUserId() != userId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 检验验证码是否唯一
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public boolean checkEmailUnique(SysUser user) {
        long userId = user.getUserId() == null ? -1L : user.getUserId();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getEmail, user.getEmail());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser != null && sysUser.getUserId() != userId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

}
