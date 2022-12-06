package com.boketto.service;

import com.boketto.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author llf
 * @Description:
 * @date 2022/11/22 22:33
 */
@Service
@RefreshScope
@AllArgsConstructor
public class HelloWorldImpl {

    @SneakyThrows
    @RabbitListener(queues = RabbitConstant.HELLO_WORLD_QUEUE)
    public void helloWorldQueue(Message message, Channel channel){
        System.out.println("接收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
