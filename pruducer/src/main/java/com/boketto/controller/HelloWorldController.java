package com.boketto.controller;

import com.boketto.constant.RabbitConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @Description: HelloWorld controller
 * @date 2022/11/22 22:36
 */
@RestController
@AllArgsConstructor
public class HelloWorldController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendHelloWorldQueue")
    public void sendHelloWorldQueue(){
        String message = "Hello World Queue";
        rabbitAdmin.declareQueue(new Queue(RabbitConstant.HELLO_WORLD_QUEUE));
        rabbitTemplate.convertAndSend("", RabbitConstant.HELLO_WORLD_QUEUE, message);
    }
}
