package shop.rns.smsbroker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static shop.rns.smsbroker.utils.rabbitmq.RabbitUtil.*;

@Configuration
public class RabbitConfig {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    // SMS QUEUE
    @Bean
    public Queue smsWorkSKTQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", SENDER_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", SKT_SENDER_ROUTING_KEY);
        args.put("x-message-ttl", WORK_TTL);
        return new Queue(SKT_WORK_QUEUE_NAME, true, false, false, args);
    }

    // SMS Exchange
    @Bean
    public DirectExchange smsExchange(){
        return new DirectExchange(SMS_EXCHANGE_NAME);
    }


    // SMS Binding
    @Bean
    public Binding bindingSmsSKT(DirectExchange smsExchange, Queue smsWorkSKTQueue){
        return BindingBuilder.bind(smsWorkSKTQueue)
                .to(smsExchange)
                .with(SKT_WORK_ROUTING_KEY);
    }

    // Send Server에게 응답 결과 전달하기 위한 큐
    @Bean
    public Queue smsReceiveSKTQueue(){
        return new Queue(SKT_RECEIVE_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange smsReceiveExchange(){
        return new DirectExchange(RECEIVE_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingSmsReceiveSKT(DirectExchange smsReceiveExchange, Queue smsReceiveSKTQueue){
        return BindingBuilder.bind(smsReceiveSKTQueue)
                .to(smsReceiveExchange)
                .with(SKT_RECEIVE_ROUTING_KEY);
    }

    // DLX QUEUE
    @Bean
    public Queue smsWaitSKTQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", WAIT_TTL);
        args.put("x-dead-letter-exchange", SMS_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", SKT_WORK_ROUTING_KEY);
        return new Queue(SKT_WAIT_QUEUE_NAME, true, false, false, args);
    }

    // DLX Exchange
    @Bean
    public DirectExchange dlxSMSExchange() {
        return new DirectExchange(WAIT_EXCHANGE_NAME);
    }


    // DLX SMS Binding
    @Bean
    public Binding bindingDLXSmsKT(DirectExchange dlxSMSExchange, Queue smsWaitSKTQueue) {
        return BindingBuilder.bind(smsWaitSKTQueue)
                .to(dlxSMSExchange)
                .with(SKT_WAIT_ROUTING_KEY);
    }
}
