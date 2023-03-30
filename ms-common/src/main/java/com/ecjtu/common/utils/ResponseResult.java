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

    private static <T> ResponseResult<T> setResult(T data, int code, String msg) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(code);
        responseResult.setData(data);
        responseResult.setMsg(msg);
        return responseResult;
    }

    public static <T> ResponseResult<T> success() {
        return setResult(null, SUCCESS, "操作成功");
    }

    public static <T> ResponseResult<T> success(T data) {
        return setResult(data, SUCCESS, "操作成功");
    }

    public static <T> ResponseResult<T> success(T data, String msg) {
        return setResult(data, SUCCESS, msg);
    }

    public static <T> ResponseResult<T> fail() {
        return setResult(null, FAIL, "操作失败");
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return setResult(null, FAIL, msg);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return setResult(data, FAIL, "操作失败");
    }

    public static <T> ResponseResult<T> fail(T data, String msg) {
        return setResult(data, FAIL, msg);
    }

    public static <T> ResponseResult<T> fail(int code, String msg) {
        return setResult(null, code, msg);
    }

    public static <T> Boolean isSuccess(ResponseResult<T> result) {
        return ResponseResult.SUCCESS == result.getCode();
    }

    public static <T> Boolean isError(ResponseResult<T> result) {
        return !isSuccess(result);
    }
}
