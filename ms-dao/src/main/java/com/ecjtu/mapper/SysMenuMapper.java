package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜单 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/4/2 12:04
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> selectMenuPermsByUserId(Long userId);

    List<String> selectMenuPermsByRoleId(Long roleId);
}
