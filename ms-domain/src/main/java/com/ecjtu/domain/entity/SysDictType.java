package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ecjtu.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 字典类型表 sys_dict_type
 * @Author: ZengXueBin
 * @Date: 2023/3/24 20:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictType extends BaseEntity {
    /**
     * 字典主键
     */
    @TableId
    private Long dictId;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 状态(0正常 1停用)
     */
    private String status;

}
