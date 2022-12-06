package com.boketto.controller;

import com.boketto.constant.RabbitConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @Description: Fanout controller
 * @date 2022/11/22 22:36
 */
@RestController
@AllArgsConstructor
public class FanoutController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendFanout")
    public void sendFanout(){
        String message = "fanoutExchange";
        FanoutExchange fanoutExchange =new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE);
        rabbitAdmin.declareExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE, "", message);
    }
}
