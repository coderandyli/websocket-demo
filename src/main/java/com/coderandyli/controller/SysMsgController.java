package com.coderandyli.controller;

import com.coderandyli.domain.WebSocketChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lizhenzhen
 * @version 1.0
 * @date 2021/3/8 上午10:32
 */
@Slf4j
@Controller
public class SysMsgController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 广播系统通知 to everyone
     */
    @ResponseBody
    @PostMapping("/sys-msg/send")
    public void sendSysNoti(@RequestBody WebSocketChatMessage webSocketChatMessage) {
        webSocketChatMessage.setSender("sys");
        webSocketChatMessage.setType("message-data");
        simpMessagingTemplate.convertAndSend("/topic/javainuse", webSocketChatMessage);
    }
}
