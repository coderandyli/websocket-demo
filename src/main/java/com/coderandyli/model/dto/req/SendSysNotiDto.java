package com.coderandyli.model.dto.req;

import lombok.Data;

/**
 * @author lizhenzhen
 * @version 1.0
 * @date 2021/3/8 上午11:08
 */
@Data
public class SendSysNotiDto {
    /**
     * 类型, 1:公告；
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;
}
