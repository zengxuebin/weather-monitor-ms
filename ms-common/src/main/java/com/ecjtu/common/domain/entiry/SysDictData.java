package com.ecjtu.common.domain.entiry;

import com.ecjtu.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 字典数据表 sys_dict_data
 * @Author: ZengXueBin
 * @Date: 2023/3/24 20:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictData extends BaseEntity {

    /**
     * 字典编码
     */
    private Long dictCode;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典值
     */
    private String dictValue;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 字典排序
     */
    private Long dictOrder;

}
