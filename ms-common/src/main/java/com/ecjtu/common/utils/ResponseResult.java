package com.ecjtu.common.utils;

import com.ecjtu.common.constant.HttpStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 服务间结果集
 * @Author: ZengXueBin
 * @Date: 2023/3/23 16:30
 */
@Data
public class ResponseResult<T> implements Serializable {

    /**
     * 成功
     */
    public static final int SUCCESS = HttpStatus.SUCCESS;
    /**
     * 失败
     */
    public static final int FAIL = HttpStatus.ERROR;

    private int code;
    private String msg;
    private T data;

    private ResponseResult() {
    }

    private static <T> ResponseResult<T> setResult(int code, String msg, T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> success() {
        return setResult(SUCCESS, "操作成功", null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return setResult(SUCCESS, "操作成功", data);
    }

    public static <T> ResponseResult<T> success(T data, String msg) {
        return setResult(SUCCESS, msg, data);
    }

    public static <T> ResponseResult<T> fail() {
        return setResult(FAIL, "操作失败", null);
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return setResult(FAIL, msg, null);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return setResult(FAIL, "操作失败", data);
    }

    public static <T> ResponseResult<T> fail(T data, String msg) {
        return setResult(FAIL, msg, data);
    }

    public static <T> ResponseResult<T> fail(int code, String msg) {
        return setResult(code, msg, null);
    }

    public static <T> Boolean isSuccess(ResponseResult<T> result) {
        return ResponseResult.SUCCESS == result.getCode();
    }

    public static <T> Boolean isError(ResponseResult<T> result) {
        return !isSuccess(result);
    }
}
