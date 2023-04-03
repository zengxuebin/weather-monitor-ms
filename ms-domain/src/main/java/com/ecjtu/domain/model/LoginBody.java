package com.ecjtu.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户登陆对象
 * @Author: ZengXueBin
 * @Date: 2023/3/24 18:50
 */
@Data
public class LoginBody implements Serializable {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 唯一标识
     */
    private String uuid;

}
