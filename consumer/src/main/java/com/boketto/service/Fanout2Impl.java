package com.boketto.service;

import com.boketto.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
public class Fanout2Impl {

    @SneakyThrows
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue (value = RabbitConstant.FANOUT_QUEUE2, durable = "true"),
                    exchange = @Exchange(value = RabbitConstant.FANOUT_EXCHANGE, type = ExchangeTypes.FANOUT, durable = "true")
            )
    )
    public void helloWorldQueue(Message message, Channel channel){
        System.out.println("Fanout2Impl接收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
