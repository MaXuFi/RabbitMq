package com.boketto.service;

import com.boketto.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2022/11/23 23:37
 */
@Service
@Slf4j
@RefreshScope
@AllArgsConstructor
public class TopicsImpl {

    @SneakyThrows
    @RabbitListener(
            bindings =@QueueBinding(
                    value = @Queue(value = RabbitConstant.TOPIC_QUEUE, durable = "true"),
                    exchange = @Exchange(value = RabbitConstant.TOPICS_EXCHANGE,type = ExchangeTypes.TOPIC, durable = "true"),
                    key = "topicKey.*"
            ))
    public void getMessage(Message message, Channel channel){
        System.out.println("TopicsImpl接收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
