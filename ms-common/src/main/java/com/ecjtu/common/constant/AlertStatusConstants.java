package com.ecjtu.common.constant;

/**
 * @Description: 预警状态常量
 * @Author: ZengXueBin
 * @Date: 2023/5/17 13:32
 */
public class AlertStatusConstants {

    private AlertStatusConstants() {
    }

    /**
     * 已过期
     */
    public static final String PROCESS_EXPIRED_ENDED = "-2";
    /**
     * 已忽略
     */
    public static final String PROCESS_IGNORED_ENDED = "-1";
    /**
     * 待处理
     */
    public static final String PENDING = "0";
    /**
     * 待推送
     */
    public static final String PENDING_PUSH = "1";
    /**
     * 待确认
     */
    public static final String PENDING_CONFIRMATION = "2";
    /**
     * 待解除
     */
    public static final String PENDING_RESOLVE = "3";
    /**
     * 待关闭
     */
    public static final String PENDING_CLOSE = "4";
    /**
     * 流程结束
     */
    public static final String PROCESS_ENDED = "5";
}
