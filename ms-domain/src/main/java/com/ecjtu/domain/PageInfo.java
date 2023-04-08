package com.ecjtu.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 分页查询条件 基类
 * @Author: ZengXueBin
 * @Date: 2023/4/8 09:55
 */
@Getter
@Setter
public class PageInfo<T> implements Serializable {

    private T entity;
    /**
     * 分页查询的页数
     */
    private Integer pageNum;
    /**
     * 分页查询的条数
     */
    private Integer pageSize;

}
