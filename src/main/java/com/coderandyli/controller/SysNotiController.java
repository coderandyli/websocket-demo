package com.coderandyli.controller;

import com.coderandyli.constant.DestinationConstant;
import com.coderandyli.model.dto.req.ReqSendSysNotiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统通知（to everyone）
 *
 * @author lizhenzhen
 * @version 1.0
 * @date 2021/3/8 上午11:04
 */
@Slf4j
@RestController
public class SysNotiController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 广播系统通知 to everyone
     */
    @PostMapping("/topic/send")
    public void sendSysNoti(@RequestBody ReqSendSysNotiDto reqSendSysNotiDto) {
        simpMessagingTemplate.convertAndSend(DestinationConstant.TOPIC_DESTINATION, reqSendSysNotiDto);
    }
}
