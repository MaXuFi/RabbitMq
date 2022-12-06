//package com.boketto.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//
///**
// * @author llf
// * @Description: 生产者confirm机制
// * @date 2022/11/25
// */
//@Component
//@AllArgsConstructor
//public class RabbitReturnConfig2{
//
//
//    final RabbitTemplate rabbitTemplate;
//
//    /**
//     * 注入到 rabbitTemplate
//     */
//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setReturnsCallback(returnedMessage -> {
//            System.out.println("消息投递至队列失败,原因:{}" + returnedMessage.getReplyText());
//            // 消息体
//            Message message = returnedMessage.getMessage();
//            // 交换机
//            String exchange = returnedMessage.getExchange();
//            // 路由键
//            String routingKey = returnedMessage.getRoutingKey();
//            // 响应码
//            int replyCode = returnedMessage.getReplyCode();
//            // 失败原因
//            String replyText = returnedMessage.getReplyText();
//        });
//    }
//}
