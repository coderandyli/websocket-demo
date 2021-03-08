package com.coderandyli.controller;

import com.coderandyli.constant.DestinationConstant;
import com.coderandyli.model.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.security.Principal;

/**
 * 消息控制器
 *
 * @author mydlq
 */
@Slf4j
@Controller
public class MessageController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 点对点发送消息，将消息发送到指定用户
     */
    @MessageMapping("/msg/send")
    public void sendUserMessage(Principal principal, MessageBody messageBody) {
        messageBody.setFrom(principal.getName());
        simpMessageSendingOperations.convertAndSendToUser(messageBody.getTargetUser(), DestinationConstant.QUEUE_DESTINATION, messageBody);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        log.error("【Error handling message】{}", exception.getMessage());
        return exception.getMessage();
    }


}
