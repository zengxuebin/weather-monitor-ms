package com.ecjtu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 数据导出记录
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:30
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeatherExport {

    /**
     * 数据导出ID
     */
    @TableId
    private Long exportId;
    /**
     * 导出用户ID
     */
    @TableField(exist = false)
    private SysUser user;
    /**
     * 导出文件名称
     */
    private String exportName;
    /**
     * 导出条件
     */
    private String exportCondition;
    /**
     * 导出格式
     */
    private String exportFormat;
    /**
     * 导出时间
     */
    private Date exportTime;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
