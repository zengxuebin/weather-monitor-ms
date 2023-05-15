package com.ecjtu.web.controller.weather;

import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.DTO.TempRangeDTO;
import com.ecjtu.domain.DTO.WeatherHistoryDTO;
import com.ecjtu.domain.model.HistoryBody;
import com.ecjtu.service.WeatherHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 历史天气
 * @Author: ZengXueBin
 * @Date: 2023/5/15 09:27
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private WeatherHistoryService historyService;

    @PostMapping("/statistics")
    public ApiResult statisticsHistory(@RequestBody HistoryBody historyBody ) {
        System.out.println(historyBody);
        // 统计量
        WeatherHistoryDTO historyDTO = historyService.statisticsWeatherHistory(historyBody.getNowCity(),
                historyBody.getStartDate(),
                historyBody.getEndDate());
        // 最高温度和最低温度
        List<Map<String, TempRangeDTO>> tempList = historyService.getTemperatureRange(historyBody.getNowCity(),
                historyBody.getStartDate(),
                historyBody.getEndDate());
        ApiResult apiResult = ApiResult.success();
        apiResult.put("statistics", historyDTO);
        apiResult.put("tempList", tempList);
        return apiResult;
    }
}
