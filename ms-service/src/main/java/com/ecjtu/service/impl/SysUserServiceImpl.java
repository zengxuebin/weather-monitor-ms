package com.ecjtu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.common.constant.UserConstants;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.mapper.SysUserMapper;
import com.ecjtu.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户 业务层实现
 * @Author: ZengXueBin
 * @Date: 2023/3/31 20:46
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectAllocateList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectUnallocateList(SysUser user) {
        return null;
    }

    @Override
    public boolean checkUsernameUnique(SysUser user) {
        Long userID = user.getUserId() == null ? -1L : user.getUserId();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null && sysUser.getUserId().longValue() != userID.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkPhoneUnique(SysUser user) {
        return false;
    }

    @Override
    public boolean checkEmailUnique(SysUser user) {
        return false;
    }

    @Override
    public int insertUser(SysUser user) {
        return 0;
    }

    @Override
    public boolean registerUser(SysUser user) {
        return sysUserMapper.insert(user) > 0;
    }

    @Override
    public int updateUser(SysUser user) {
        return 0;
    }

    @Override
    public void authorizeUser(Long userId, Long[] roleIds) {

    }

    @Override
    public boolean updateUserAvatar(String username, String avatar) {
        return false;
    }

    @Override
    public int resetPassword(SysUser user) {
        return 0;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        return 0;
    }
}
