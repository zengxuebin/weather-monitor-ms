package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 12:03
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
