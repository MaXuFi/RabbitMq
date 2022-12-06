package com.boketto.controller;

import com.boketto.constant.RabbitDelayConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author llf
 * @Description: Routing controller
 * @date 2022/11/22 22:36
 */
@RestController
@AllArgsConstructor
public class DelayController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendDelay")
    public void sendDead(@RequestParam("delayTime") String delayTime){
        //消息内容
        String transmitMessage ="delayMessage,延迟时间是:" + delayTime;

        //声明正常交换机
        Exchange directExchange = ExchangeBuilder.directExchange(RabbitDelayConstant.CHECK_DELAY_EXCHANGE).durable(true).build();
        rabbitAdmin.declareExchange(directExchange);

        //声明延迟交换机
        Exchange delayExchange = ExchangeBuilder.directExchange(RabbitDelayConstant.DELAY_EXCHANGE).durable(true).build();
        rabbitAdmin.declareExchange(delayExchange);

        //延迟队列
        Queue delayQueue = QueueBuilder.durable(RabbitDelayConstant.DELAY_QUEUE).build();

        Map<String,Object> delayMap = new HashMap<>(3);
        // 设置过期时间
        //delayMap.put("x-message-ttl",Long.valueOf(delayTime));

        // 设置死信队列
        delayMap.put("x-dead-letter-exchange",RabbitDelayConstant.DELAY_EXCHANGE);
        // 设置 路由key
        delayMap.put("x-dead-letter-routing-key",RabbitDelayConstant.DELAY_KEY);
        Queue normalQueue = QueueBuilder.durable(RabbitDelayConstant.CHECK_DELAY_QUEUE).withArguments(delayMap).build();
        //正常队列
        rabbitAdmin.declareQueue(normalQueue);

        rabbitAdmin.declareQueue(delayQueue);

        //声明普通绑定关系
        rabbitAdmin.declareBinding(
                BindingBuilder.bind(normalQueue).to(directExchange).with(RabbitDelayConstant.CHECK_DELAY_KEY).noargs()
        );

        //声明死信绑定关系
        rabbitAdmin.declareBinding(
                BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitDelayConstant.DELAY_KEY).noargs()
        );

        rabbitTemplate.convertAndSend(RabbitDelayConstant.CHECK_DELAY_EXCHANGE, RabbitDelayConstant.CHECK_DELAY_KEY, transmitMessage, messageProcessor -> {
             messageProcessor.getMessageProperties().setExpiration(delayTime);
            return messageProcessor;
        });
    }
}
