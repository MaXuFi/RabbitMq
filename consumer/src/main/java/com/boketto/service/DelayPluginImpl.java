package com.boketto.service;

import com.boketto.constant.RabbitDelayConstant;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
 * @date 2022/11/23 22:54
 */
@Service
@AllArgsConstructor
@RefreshScope
public class DelayPluginImpl {

    @SneakyThrows
    @RabbitListener(
            bindings =@QueueBinding(
                    value = @Queue(value = RabbitDelayConstant.DELAY_PLUGIN_QUEUE, durable = "true"),
                    exchange = @Exchange(value = RabbitDelayConstant.DELAY_PLUGIN_EXCHANGE,type ="x-delayed-message", durable = "true"),
                    key = RabbitDelayConstant.DELAY_PLUGIN_KEY
            ))
    public void getMessage(Message message, Channel channel){
        System.out.println("DelayPluginImpl接收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        //获取业务消息id
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
