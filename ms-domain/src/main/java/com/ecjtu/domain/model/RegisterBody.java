package com.ecjtu.domain.model;

import jakarta.validation.constraints.Email;

/**
 * @Description: 用户注册对象
 * @Author: ZengXueBin
 * @Date: 2023/4/3 08:35
 */
public class RegisterBody extends LoginBody {

    private String phone;
    private String sex;
    @Email
    private String email;

}
