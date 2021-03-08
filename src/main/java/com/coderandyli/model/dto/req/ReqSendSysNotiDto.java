package com.coderandyli.model.dto.req;

import lombok.Data;

/**
 * 发送系统通知
 *
 * @author lizhenzhen
 * @version 1.0
 * @date 2021/3/8 上午11:23
 */
@Data
public class ReqSendSysNotiDto {
    /**
     * 类型，1：公告
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;

}
