package com.boketto.controller;

import com.boketto.constant.RabbitConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
public class DeadController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendDead")
    public void sendDead(){
        //消息内容
        String transmitMessage ="normal_to_deadMessage";

        //声明正常交换机
        DirectExchange directExchange = new DirectExchange(RabbitConstant.NORMAL_EXCHANGE);
        rabbitAdmin.declareExchange(directExchange);

        //死信交换机
        DirectExchange deadExchange = new DirectExchange(RabbitConstant.DEAD_EXCHANGE);
        rabbitAdmin.declareExchange(deadExchange);

        //死信队列
        Queue deadQueue = QueueBuilder.durable(RabbitConstant.DEAD_QUEUE).build();

        Map<String,Object> args = new HashMap<>(4);
        // 设置过期时间
        args.put("x-message-ttl",5000);
        // 设置队列最大数
        args.put("x-max-length",3);
        // 设置死信队列
        args.put("x-dead-letter-exchange",RabbitConstant.DEAD_EXCHANGE);
        // 设置 路由key
        args.put("x-dead-letter-routing-key",RabbitConstant.DEAD_KEY);
        //正常队列
        Queue normalQueue = new Queue(RabbitConstant.NORMAL_QUEUE,true,false,false,args);


        rabbitAdmin.declareQueue(normalQueue);
        rabbitAdmin.declareQueue(deadQueue);

        //声明普通绑定关系
        rabbitAdmin.declareBinding(
                BindingBuilder.bind(normalQueue).to(directExchange).with(RabbitConstant.NORMAL_KEY)
        );

        //声明死信绑定关系
        rabbitAdmin.declareBinding(
                BindingBuilder.bind(deadQueue).to(deadExchange).with(RabbitConstant.DEAD_KEY)
        );

        rabbitTemplate.convertAndSend(RabbitConstant.NORMAL_EXCHANGE, RabbitConstant.NORMAL_KEY, transmitMessage);
    }
}
