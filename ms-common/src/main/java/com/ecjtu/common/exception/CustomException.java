package com.ecjtu.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 自定义异常
 * @Author: ZengXueBin
 * @Date: 2023/3/30 15:39
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(String message) {
        this.message = message;
    }
}
