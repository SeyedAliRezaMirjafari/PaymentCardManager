package com.sed.notification_service.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory(String rabbitHost, String rabbitPort, String rabbitUsername, String rabbitPassword) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost, Integer.parseInt(rabbitPort));
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rubeExchangeTemplate(@Value("${spring.rabbitmq.host}") String rabbitHost,
                                               @Value("${spring.rabbitmq.port}") String rabbitPort,
                                               @Value("${spring.rabbitmq.username}") String rabbitUsername,
                                               @Value("${spring.rabbitmq.password}") String rabbitPassword) throws UnsupportedEncodingException {
        final RabbitTemplate r = new RabbitTemplate(connectionFactory(rabbitHost, rabbitPort,
                new String(rabbitUsername.getBytes(), "UTF-8"),
                new String(rabbitPassword.getBytes(), "UTF-8")));
        /*r.setExchange("rmq.rube.exchange");
        r.setRoutingKey("rube.key");*/
        r.setConnectionFactory(connectionFactory(rabbitHost, rabbitPort,
                new String(rabbitUsername.getBytes(), "UTF-8"),
                new String(rabbitPassword.getBytes(), "UTF-8")));
        return r;
    }




    @Bean
    Queue queue() {
        return new Queue("sms", false);
    }

    @Autowired
    RabbitTemplate rabbitTemplate;
}












