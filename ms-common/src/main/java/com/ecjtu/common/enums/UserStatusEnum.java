package com.ecjtu.common.enums;

/**
 * @Description: 用户状态
 * @Author: ZengXueBin
 * @Date: 2023/3/24 18:40
 */
public enum UserStatusEnum {

    OK("0", "正常"),
    DELETED("1", "删除"),
    DISABLE("2", "停用");

    private final String code;
    private final String info;

    UserStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
