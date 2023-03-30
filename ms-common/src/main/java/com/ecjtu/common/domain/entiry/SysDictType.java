package com.ecjtu.common.domain.entiry;

import com.ecjtu.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
