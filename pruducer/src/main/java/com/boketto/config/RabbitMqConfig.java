package com.boketto.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author llf
 * @Description: rabbit 连接配置信息
 * @date 2022/11/22 16:59
 */
@Configuration(proxyBeanMethods = false)
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    @Bean
    public ConnectionFactory connectionFactory() {
        // RabbitTemplate使用CachingConnectionFactory作为连接工厂
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        factory.setPublisherReturns(true);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        //RabbitAdmin 该类封装了对 RabbitMQ 的管理操作
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // true:消息通过交换器无法匹配到队列会返回给生产者 并触发MessageReturn
        // false:匹配不到会直接被丢弃
        rabbitTemplate.setMandatory(true);
        MessageConverter messageConverter = new Jackson2JsonMessageConverter();
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //开启手动 ack
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
