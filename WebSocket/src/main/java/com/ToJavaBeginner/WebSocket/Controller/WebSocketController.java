package com.ToJavaBeginner.WebSocket.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

//    @MessageMapping("/chat") // 定义消息映射路径
//    @SendTo("/topic/chat") // 将消息发送到指定的消息代理
//    public ChatMessage send(ChatMessage message) {
//        return message; // 返回消息给订阅者
//    }
}
