package com.ecjtu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.domain.entity.WeatherExport;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 气象数据导出 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/5/4 03:59
 */
@Mapper
public interface WeatherExportMapper extends BaseMapper<WeatherExport> {
}
