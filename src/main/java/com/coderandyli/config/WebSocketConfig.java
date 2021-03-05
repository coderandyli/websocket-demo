package com.coderandyli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * WebSocket 配置类
 *
 * @author mydlq
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 配置消息代理选项
     *
     * @param registry 消息代理注册配置
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/topic", "/queue") // 设置广播的基础路径  topic: 发布订阅；queue: 点对点
                .setRelayHost("192.168.30.12")
                .setRelayPort(61613)
                .setClientLogin("guest") // default value is [guest]
                .setClientPasscode("guest") // default value is [guest]
                .setSystemLogin("guest") // default value is [guest]
                .setSystemPasscode("guest") // default value is [guest]
        ;

        // 定义客户端发送消息前缀，该前缀会筛选消息目标转发到 Controller 类中@MessageMapping注解对应的方法里
        registry.setApplicationDestinationPrefixes("/app"); // 设置客户端订阅消息的基础路径
        // 服务端通知客户端的前缀，可以不设置，默认为user
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * 配置 WebSocket 进入点，及开启使用 SockJS，这些配置主要用配置连接端点，用于 WebSocket 连接
     *
     * @param registry STOMP 端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 配置 websocket 进入点
        registry.addEndpoint("/ad-websocket")
                .addInterceptors(new HttpHandshakeInterceptor())
                .setHandshakeHandler(new HttpHandshakeHandler())
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /**
     * 与处理从 WebSocket 客户端接收和发送到 WebSocket 的消息相关的配置选项。
     *
     * @param registry STOMP 端点
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(new HttpWebSocketHandlerDecoratorFactory());
    }


    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(createUserInterceptor());

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(createUserInterceptor());
    }

    /*将客户端渠道拦截器加入spring ioc容器*/
    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }

}