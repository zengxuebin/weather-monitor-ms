package com.ecjtu.common.constant;

/**
 * @Description: 邮件预警信息模板
 * @Author: ZengXueBin
 * @Date: 2023/5/17 19:40
 */
public class AlertTemplateConstants {

    /**
     * 邮件预警详情模板
     */
    public static final String ALERT_TEMPLATE = "国家预警信息发布中心在于{triggerTime}" +
            "在{alertArea}发布{alertTitle}信号，本次预警的时间段为{startTime}至{endTime}，" +
            "预警类型为{alertType},请大家注意防范";
}
