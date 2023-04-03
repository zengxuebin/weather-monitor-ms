package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户类 mapper
 * @Author: ZengXueBin
 * @Date: 2023/4/1 22:40
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
