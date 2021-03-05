package com.coderandyli.config;

import com.coderandyli.model.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.sockjs.support.AbstractSockJsService;
import org.springframework.web.socket.sockjs.transport.TransportType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/**
 * WebSocket 握手处理器
 *
 * @author mydlq
 */
public class HttpHandshakeHandler extends DefaultHandshakeHandler {

    /**
     * 用于与正在建立会话过程中的 WebSocket 的用户相关联的方法，可以在此处配置进行关联的用户信息。
     *
     * @param request    握手请求对象
     * @param wsHandler  WebSocket 处理器
     * @param attributes 从 HTTP 握手到与 WebSocket 会话关联的属性
     * @return 对于 WebSocket 的会话的用户或者 null
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        User user = fetchUser(request);
        return user::getUsername;
    }

    /**
     * 从Session中获取用户信息
     *
     * @param request
     * @return
     */
    private User fetchUser(ServerHttpRequest request) {
        ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
        // 获取 HTTP Session 对象
        HttpSession session = serverRequest.getServletRequest().getSession();
        // 从 HTTP Session 中获取用户信息
        return (User) session.getAttribute("user");
    }
}