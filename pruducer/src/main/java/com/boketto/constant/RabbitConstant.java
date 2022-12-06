package com.boketto.constant;

/**
 * @author llf
 * @Description: RabbitMQ组件相关常亮
 * @date 2022/11/22 22:25
 */
public interface RabbitConstant {

     String HELLO_WORLD_QUEUE= "helloWorldQueue";

     String WORK_QUEUE= "workQueue";

     String DEAD_QUEUE= "deadQueue";

     String NORMAL_QUEUE= "normalQueue";

     String FANOUT_EXCHANGE= "fanoutExchange";

     String ROUTING_EXCHANGE= "routingExchange";

     String DEAD_EXCHANGE= "deadExchange";

     String NORMAL_EXCHANGE= "normalExchange";

     String TOPICS_EXCHANGE= "topicsExchange";

     String ROUTING_KEY= "routingKey";

     String DEAD_KEY= "deadKey";

     String NORMAL_KEY= "normalKey";

     String TOPICS_KEY= "topicKey.add";

}
