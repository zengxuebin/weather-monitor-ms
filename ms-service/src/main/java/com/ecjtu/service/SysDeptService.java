package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.SysDept;

/**
 * @Description: 部门 业务层
 * @Author: ZengXueBin
 * @Date: 2023/4/9 09:33
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 校验部门是否唯一
     * @param dept 部门
     * @return 结果
     */
    boolean checkDeptNameUnique(SysDept dept);
}
