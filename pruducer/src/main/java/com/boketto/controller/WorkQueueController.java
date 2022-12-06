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
 * @Description: WorkQueue controller
 * @date 2022/11/22 22:36
 */
@RestController
@AllArgsConstructor
public class WorkQueueController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendWorkQueue")
    public void sendWorkQueue(){
        String message = "Hello Work Queue";
        rabbitAdmin.declareQueue(new Queue(RabbitConstant.WORK_QUEUE));
        rabbitTemplate.convertAndSend("", RabbitConstant.WORK_QUEUE, message);
    }
}
