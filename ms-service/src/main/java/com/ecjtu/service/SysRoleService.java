package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.SysRole;

import java.util.Set;

/**
 * @Description: 角色 业务层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 12:02
 */
public interface SysRoleService extends IService<SysRole> {

    Set<String> selectRolePermissionByUserId(Long userId);
}
