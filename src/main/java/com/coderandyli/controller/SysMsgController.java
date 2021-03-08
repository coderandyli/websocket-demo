package com.coderandyli.controller;

import com.coderandyli.domain.WebSocketChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 广播系统消息 to everyone
     */
    @ResponseBody
    @PostMapping("/sys-msg/send")
    public void sendSysNoti() {
        WebSocketChatMessage webSocketChatMessage = new WebSocketChatMessage();
        webSocketChatMessage.setSender("sys");
        webSocketChatMessage.setType("message-data");
        webSocketChatMessage.setContent("夜深了，天凉了，注意盖好被子...");
        simpMessageSendingOperations.convertAndSend("/topic/javainuse", webSocketChatMessage);
    }
}
