package com.ecjtu.web.controller.alert;

import com.ecjtu.common.constant.AlertResultConstants;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.service.WeatherAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 忽略预警
 * @Author: ZengXueBin
 * @Date: 2023/5/17 23:04
 */
@RestController
@RequestMapping("/alert/ignore")
public class AlertIgnoreController {

    @Autowired
    private WeatherAlertService weatherAlertService;

    @PostMapping("/handle")
    public ApiResult handleAlertIgnore(@RequestBody List<Long> alertIds) {
        weatherAlertService.updateAlertStatus(alertIds, 1);
        for (Long alertId : alertIds) {
            weatherAlertService.recordAlertLog(alertId, AlertResultConstants.PUBLISHED);
        }
        return ApiResult.success();
    }
}
