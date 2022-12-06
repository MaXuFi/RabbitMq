package com.boketto.controller;

import com.boketto.constant.RabbitDelayConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
public class DelayPluginController {

     RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendDelayPlugin")
    public void sendDelayPlugin(@RequestParam("delayTime") Integer delayTime){

        rabbitTemplate.setMandatory(false);

        //消息内容
        String transmitMessage ="sendDelayPlugin,延迟时间是:" + delayTime;

        Map<String,Object> delayMap = new HashMap<>(1);
        //根据路由策略设置direct
        delayMap.put("x-delayed-type","direct");

        //自定义类型延迟交换机
        CustomExchange customExchange = new CustomExchange(RabbitDelayConstant.DELAY_PLUGIN_EXCHANGE,"x-delayed-message",true,false,delayMap);
        rabbitAdmin.declareExchange(customExchange);

        Queue delayPluginQueue = QueueBuilder.durable(RabbitDelayConstant.DELAY_PLUGIN_QUEUE).build();
        rabbitAdmin.declareQueue(delayPluginQueue);

        //声明延迟绑定关系
        rabbitAdmin.declareBinding(
                BindingBuilder.bind(delayPluginQueue).to(customExchange).with(RabbitDelayConstant.DELAY_PLUGIN_KEY).noargs()
        );

        rabbitTemplate.convertAndSend(RabbitDelayConstant.DELAY_PLUGIN_EXCHANGE, RabbitDelayConstant.DELAY_PLUGIN_KEY, transmitMessage, messageProcessor -> {
            //设置延迟时间
             messageProcessor.getMessageProperties().setDelay(delayTime);
            return messageProcessor;
        });
    }
}
