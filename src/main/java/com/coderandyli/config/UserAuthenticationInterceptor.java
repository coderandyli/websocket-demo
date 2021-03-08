package com.coderandyli.config;

import com.coderandyli.model.User;
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
public class UserAuthenticationInterceptor implements ChannelInterceptor {
    private static final String AUTH_TOKEN_KEY = "Auth-Token";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader(AUTH_TOKEN_KEY);
            log.info("the token value is 【{}】", token);

            // TODO: 2021/3/8 通过token获取用户信息
            User user = new User();
            user.setName("name01");
            user.setUsername("userName01");
            accessor.setUser(user);
        }
        log.info("消息发送之前");
        return message;
    }
}
