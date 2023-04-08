package com.ecjtu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecjtu.domain.entity.SysDictData;

import java.util.List;

/**
 * @Description: 字典 业务层
 * @Author: ZengXueBin
 * @Date: 2023/4/8 08:40
 */
public interface SysDictDataService extends IService<SysDictData> {

    /**
     * 新增保存字典数据信息
     * @param dict 字典数据
     * @return 结果
     */
    int insertDictData(SysDictData dict);

    /**
     * 修改保存字典数据信息
     * @param dict 字典数据
     * @return 结果
     */
    int updateDictData(SysDictData dict);

    /**
     * 批量删除字典数据信息
     * @param dictCodeList 需要删除的字典id
     */
    void deleteDictByIds(List<Long> dictCodeList);
}
