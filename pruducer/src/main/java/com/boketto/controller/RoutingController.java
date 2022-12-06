package com.boketto.controller;

import com.boketto.constant.RabbitConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @Description: Routing controller
 * @date 2022/11/22 22:36
 */
@RestController
@AllArgsConstructor
public class RoutingController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendRouting")
    public void sendRouting(){
        String message = "routingExchange";
        DirectExchange directExchange = new DirectExchange(RabbitConstant.ROUTING_EXCHANGE);
        rabbitAdmin.declareExchange(directExchange);
        CorrelationData correlationData = new CorrelationData();
        //设置唯一Id
        correlationData.setId(rabbitTemplate.getUUID());
        rabbitTemplate.convertAndSend(RabbitConstant.ROUTING_EXCHANGE, RabbitConstant.ROUTING_KEY, message, correlationData);
    }
}
