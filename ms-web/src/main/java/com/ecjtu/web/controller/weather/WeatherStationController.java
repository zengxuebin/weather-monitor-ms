package com.ecjtu.web.controller.weather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.DTO.StationCountDTO;
import com.ecjtu.domain.DTO.StationTypeCountDTO;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.WeatherStation;
import com.ecjtu.domain.model.WeatherStationQuery;
import com.ecjtu.service.WeatherStationService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 气象监测站
 * @Author: ZengXueBin
 * @Date: 2023/5/14 13:57
 */
@RestController
@RequestMapping("/station")
public class WeatherStationController {

    @Autowired
    private WeatherStationService stationService;

    /**
     * 查询所有监测站
     * @return 所有监测站
     */
    @GetMapping("/list")
    public ApiResult queryAllStation() {
        List<WeatherStation> stationList = stationService.list();
        return ApiResult.success(stationList);
    }

    /**
     * 分页查询监测站
     * @param query 查询条件
     * @return 监测站
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherStationQuery> query) {
        System.out.println(query);
        LambdaQueryWrapper<WeatherStation> queryWrapper = new LambdaQueryWrapper<>();
        WeatherStationQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getStationProvince())) {
                queryWrapper.eq(WeatherStation::getStationProvince, entity.getStationProvince());
            }
            if (StringUtils.isNotBlank(entity.getStationCity())) {
                queryWrapper.eq(WeatherStation::getStationCity, entity.getStationCity());
            }
            if (StringUtils.isNotBlank(entity.getStationType())) {
                queryWrapper.eq(WeatherStation::getStationType, entity.getStationType());
            }
        }

        Page<WeatherStation> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(stationService.page(page, queryWrapper));
    }

    /**
     * 获取城市列表
     * @return 城市列表
     */
    @GetMapping("/city/list")
    public ApiResult queryAllCity() {
        List<Map<String, Object>> cityList = stationService.getAllCity();
        return ApiResult.success(cityList);
    }

    /**
     * 根据城市查询监测站
     * @param city 城市
     * @return 监测站列表
     */
    @GetMapping("/list/{city}")
    public ApiResult queryStationByCity(@PathVariable String city) {
        LambdaQueryWrapper<WeatherStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeatherStation::getStationCity, city);
        List<WeatherStation> stationList = stationService.list(queryWrapper);
        return ApiResult.success(stationList);
    }

    /**
     * 统计监测站
     * @return 监测站统计量
     */
    @GetMapping("/count")
    public ApiResult countStation() {
        // 统计各个城市监测站个数
        List<StationCountDTO> countDTO = stationService.countStation();
        // 统计监测站类型个数
        List<StationTypeCountDTO> typeCountDTO = stationService.countStationType();
        ApiResult apiResult = ApiResult.success();
        apiResult.put("cityCount", countDTO);
        apiResult.put("typeCount", typeCountDTO);
        return apiResult;
    }
}
