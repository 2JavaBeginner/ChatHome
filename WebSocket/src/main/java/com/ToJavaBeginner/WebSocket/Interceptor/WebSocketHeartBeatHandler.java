package com.ToJavaBeginner.WebSocket.Interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;


/*
* Interceptor拦截器
* 对消息进行处理
*
* WebSocketHeartBeatHandler：心跳包，确定session是否存活
* */
public class WebSocketHeartBeatHandler implements HandlerInterceptor {
    // 发送心跳包的时间间隔
    private final int HEARTBEAT_INTERVAL = 10;

    //@Override
    public boolean preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        // 判断是否为心跳消息
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            SimpMessageHeaderAccessor simpAccessor = SimpMessageHeaderAccessor.create();
            simpAccessor.setSessionAttributes(new ConcurrentHashMap<>(16));
            accessor.setHeader(SimpMessageHeaderAccessor.HEART_BEAT_HEADER, HEARTBEAT_INTERVAL + "," + HEARTBEAT_INTERVAL);
        }
        // 其他消息则直接通过ChannelInterceptorAdapter过滤而不做任何处理
        return true;
    }
}
