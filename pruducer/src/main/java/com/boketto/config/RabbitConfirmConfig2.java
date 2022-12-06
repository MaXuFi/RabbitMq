//package com.boketto.config;
//
//import lombok.AllArgsConstructor;
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
//public class RabbitConfirmConfig2{
//
//
//    final RabbitTemplate rabbitTemplate;
//
//    /**
//     * 注入到 rabbitTemplate
//     */
//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback((correlationData, ack, reason)->{
//            if(ack){
//                //消息投递成功逻辑
//                System.out.println("消息成功投递到交换机");
//            }else {
//                //消息投递失败逻辑
//                System.out.println("消息未投递到交换机, 原因是:" + reason);
//            }
//        });
//    }
//
//}
