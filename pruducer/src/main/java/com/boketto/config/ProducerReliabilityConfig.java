//package com.boketto.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.ReturnedMessage;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
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
//public class ProducerReliabilityConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
//
//
//    final RabbitTemplate rabbitTemplate;
//
//    /**
//     * 注入到 rabbitTemplate
//     */
//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnsCallback(this);
//    }
//
//
//
//    /**
//     *
//     * @param correlationData 消息原数据
//     * @param ack 交换机是否成功接收到消息
//     * @param reason 失败原因
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String reason) {
//
//        if(ack){
//            //消息投递成功逻辑
//            System.out.println("消息成功投递到交换机");
//        }else {
//            //消息投递失败逻辑
//            System.out.println("消息未投递到交换机, 原因是:" + reason);
//        }
//
//    }
//
//    @Override
//    public void returnedMessage(ReturnedMessage returnedMessage) {
//        System.out.println("消息投递至队列失败,原因:{}" + returnedMessage.getReplyText());
//        // 消息体
//        Message message = returnedMessage.getMessage();
//        // 交换机
//        String exchange = returnedMessage.getExchange();
//        // 路由键
//        String routingKey = returnedMessage.getRoutingKey();
//        // 响应码
//        int replyCode = returnedMessage.getReplyCode();
//        // 失败原因
//        String replyText = returnedMessage.getReplyText();
//    }
//}
