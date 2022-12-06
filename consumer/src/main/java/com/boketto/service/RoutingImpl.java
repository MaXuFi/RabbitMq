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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author llf
 * @Description:
 * @date 2022/11/23 22:54
 */
@Service
@AllArgsConstructor
@RefreshScope
public class RoutingImpl {

    final RedisTemplate redisTemplate;

    @SneakyThrows
    @RabbitListener(
            bindings =@QueueBinding(
                value = @Queue(value = RabbitConstant.ROUTING_QUEUE, durable = "true"),
                exchange = @Exchange(value = RabbitConstant.ROUTING_EXCHANGE,type = ExchangeTypes.DIRECT, durable = "true"),
                key = RabbitConstant.ROUTING_KEY
    ))
    public void getMessage(Message message, Channel channel){
        System.out.println("RoutingImpl接收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        Map<String, Object> headersMap = message.getMessageProperties().getHeaders();
        //获取业务消息id
        String messageId = headersMap.get("spring_returned_message_correlation").toString();
        Boolean locked = redisTemplate.opsForValue().setIfAbsent(messageId, 1, 60000, TimeUnit.MILLISECONDS);
        if(locked){
            // 做消费

            //手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else {
            //重复消费

            //将消息reject
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
