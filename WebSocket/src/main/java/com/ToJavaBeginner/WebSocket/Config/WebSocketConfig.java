package com.ToJavaBeginner.WebSocket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
    * @Configuration表明是Spring类
    * @EnableWebSocketMessageBroker 启动消息代理，{Broker：代理}
    * 重写registerStompEndpoints方法，注册WebSocket端点
    * 重写configureMessageBroker方法，配置代理，选择接收消息前缀
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();  // 定义WebSocket端点
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
        * config.setApplicationDestinationPrefixes("/app")定义了应用程序接收消息的前缀，
        * 例如要接收"/app/chat"的消息，需要定义被@MessageMapping("/chat")注解的方法。
        * */
        config.enableSimpleBroker("/topic");  // 定义消息代理，用于点对点通信
        config.setApplicationDestinationPrefixes("/app");       // 定义应用程序前缀
    }

}
