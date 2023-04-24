package com.ecjtu.web.controller.system;

import com.ecjtu.web.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 注册
 * @Author: ZengXueBin
 * @Date: 2023/4/9 20:53
 */
@RestController
@RequestMapping("/auth")
public class SysRegisterController {

    @Autowired
    private SysRegisterService registerService;


}
