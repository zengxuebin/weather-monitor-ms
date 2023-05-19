package com.ecjtu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: Mail 实体
 * @Author: ZengXueBin
 * @Date: 2023/5/20 01:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {

    /**
     * 邮件接收方
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    public void setTos(String... recipients) {
        this.tos = recipients;
    }
}
