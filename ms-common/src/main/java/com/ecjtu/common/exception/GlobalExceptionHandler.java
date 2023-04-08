package com.ecjtu.common.exception;

import com.ecjtu.common.constant.HttpStatus;
import com.ecjtu.common.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

/**
 * @Description: 全局异常处理器
 * @Author: ZengXueBin
 * @Date: 2023/3/30 15:28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     * @param e 权限异常
     * @return 异常消息
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult handleAccessDeniedException(AccessDeniedException e) {
        log.error(String.valueOf(e));
        return ApiResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    /**
     * 系统异常
     * @param e 异常
     * @return 异常消息
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        e.printStackTrace();
        System.out.println("ddd");
        return ApiResult.error(e.getMessage());
    }

    /**
     * 自定义异常
     * @param e 自定义异常
     * @return 自定义异常消息
     */
    @ExceptionHandler(CustomException.class)
    public ApiResult handleCustomException(CustomException e) {
        log.error(String.valueOf(e));
        return ApiResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 自定义验证异常
     * @param e 验证异常
     * @return 验证异常消息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(String.valueOf(e));
        String defaultMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ApiResult.error(defaultMessage);
    }

    /**
     * 表单绑定bean出错时抛出
     * @param e 绑定异常
     * @return 异常消息
     */
    @ExceptionHandler(BindException.class)
    public ApiResult handleException(BindException e) {
        log.error(String.valueOf(e));
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        return ApiResult.error(defaultMessage);
    }

}
