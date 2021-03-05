package com.coderandyli.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * @author lizhenzhen
 * @version 1.0
 * @date 2021/3/1 下午4:44
 */
@Slf4j
public class UserInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            User user = (User)accessor.getSessionAttributes().get(Constants.WEBSOCKET_USER_KEY);
//            accessor.setUser(user);
        }
        log.info("消息发送之前");
        return message;
    }
}
