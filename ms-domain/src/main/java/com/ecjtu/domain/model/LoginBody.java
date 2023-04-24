package com.ecjtu.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @NotBlank(message = "用户名不能为空")
    @Length(min = 2, max = 16, message = "用户名长度必须在2-16之间")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 16, message = "用户名长度必须在5-16之间")
    private String password;
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;
    /**
     * 唯一标识
     */
    private String uuid;

}
