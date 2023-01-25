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
<<<<<<< HEAD
    public MessageConverter jsonMessageConverter() {
=======
    public MessageConverter jsonMessageConverter(){
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
        return new Jackson2JsonMessageConverter();
    }

    @Bean
<<<<<<< HEAD
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
=======
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    // SMS QUEUE
    @Bean
<<<<<<< HEAD
    Queue smsWorkKTQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", KT_WAIT_ROUTING_KEY);
        return new Queue(KT_WORK_QUEUE_NAME, true, false, false, args);
=======
    public Queue smsWorkSKTQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", SKT_WAIT_ROUTING_KEY);
        return new Queue(SKT_WORK_QUEUE_NAME, true, false, false, args);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }

    // SMS Exchange
    @Bean
<<<<<<< HEAD
    public DirectExchange smsExchange() {
        return new DirectExchange(SMS_EXCHANGE_NAME);
    }

    // SMS Binding
    @Bean
    public Binding bindingSmsKT(DirectExchange smsExchange, Queue smsWorkKTQueue) {
        return BindingBuilder.bind(smsWorkKTQueue)
                .to(smsExchange)
                .with(KT_WORK_ROUTING_KEY);
=======
    public DirectExchange smsExchange(){
        return new DirectExchange(SMS_EXCHANGE_NAME);
    }


    // SMS Binding
    @Bean
    public Binding bindingSmsSKT(DirectExchange smsExchange, Queue smsWorkSKTQueue){
        return BindingBuilder.bind(smsWorkSKTQueue)
                .to(smsExchange)
                .with(SKT_WORK_ROUTING_KEY);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }

    // Send Server에게 응답 결과 전달하기 위한 큐
    @Bean
<<<<<<< HEAD
    Queue smsReceiveKTQueue() {
        return new Queue(KT_RECEIVE_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange smsReceiveExchange() {
=======
    public Queue smsReceiveSKTQueue(){
        return new Queue(SKT_RECEIVE_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange smsReceiveExchange(){
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
        return new DirectExchange(RECEIVE_EXCHANGE_NAME);
    }

    @Bean
<<<<<<< HEAD
    public Binding bindingSmsReceiveKT(DirectExchange smsReceiveExchange, Queue smsReceiveKTQueue) {
        return BindingBuilder.bind(smsReceiveKTQueue)
                .to(smsReceiveExchange)
                .with(KT_RECEIVE_ROUTING_KEY);
=======
    public Binding bindingSmsReceiveSKT(DirectExchange smsReceiveExchange, Queue smsReceiveSKTQueue){
        return BindingBuilder.bind(smsReceiveSKTQueue)
                .to(smsReceiveExchange)
                .with(SKT_RECEIVE_ROUTING_KEY);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }

    // DLX QUEUE
    @Bean
<<<<<<< HEAD
    public Queue smsWaitKTQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", WAIT_TTL);
        args.put("x-dead-letter-exchange", SMS_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", KT_WORK_ROUTING_KEY);
        return new Queue(KT_WAIT_QUEUE_NAME, true, false, false, args);
=======
    public Queue smsWaitSKTQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", WAIT_TTL);
        args.put("x-dead-letter-exchange", SMS_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", SKT_WORK_ROUTING_KEY);
        return new Queue(SKT_WAIT_QUEUE_NAME, true, false, false, args);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }

    // DLX Exchange
    @Bean
    public DirectExchange dlxSMSExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME);
    }


    // DLX SMS Binding
    @Bean
<<<<<<< HEAD
    public Binding bindingDLXSmsKT(DirectExchange dlxSMSExchange, Queue smsWaitKTQueue) {
        return BindingBuilder.bind(smsWaitKTQueue)
                .to(dlxSMSExchange)
                .with(KT_WAIT_ROUTING_KEY);
=======
    public Binding bindingDLXSmsKT(DirectExchange dlxSMSExchange, Queue smsWaitSKTQueue) {
        return BindingBuilder.bind(smsWaitSKTQueue)
                .to(dlxSMSExchange)
                .with(SKT_WAIT_ROUTING_KEY);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }
}
