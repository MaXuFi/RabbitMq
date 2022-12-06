package com.boketto.controller;

import com.boketto.constant.RabbitConstant;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
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
public class TopicsController {

    final RabbitTemplate rabbitTemplate;

    final RabbitAdmin rabbitAdmin;

    @GetMapping("/sendTopics")
    public void sendTopics(){
        String message = "sendTopics";
        TopicExchange topicExchange = new TopicExchange(RabbitConstant.TOPICS_EXCHANGE);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitTemplate.convertAndSend(RabbitConstant.TOPICS_EXCHANGE, RabbitConstant.TOPICS_KEY, message);
    }

}
