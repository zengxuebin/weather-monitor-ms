package com.ecjtu.web.controller.system;

import com.ecjtu.common.config.ProjectConfig;
import com.ecjtu.common.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 首页
 * @Author: ZengXueBin
 * @Date: 2023/4/9 12:05
 */
@RestController
public class SysIndexController {

    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 首页信息
     * @return 首页信息
     */
    @RequestMapping("/")
    public ApiResult index() {
        return ApiResult.success(projectConfig);
    }
}
