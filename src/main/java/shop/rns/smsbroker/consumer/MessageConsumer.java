package shop.rns.smsbroker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
<<<<<<< HEAD
import org.springframework.messaging.handler.annotation.Header;
=======
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
import org.springframework.stereotype.Component;
import shop.rns.smsbroker.config.status.MessageStatus;
import shop.rns.smsbroker.dlx.DlxProcessingErrorHandler;
import shop.rns.smsbroker.dto.broker.ReceiveMessageDto;
import shop.rns.smsbroker.dto.message.MessageResultDto;
import shop.rns.smsbroker.dto.sms.SmsMessageDto;

import java.io.IOException;

<<<<<<< HEAD
import static shop.rns.smsbroker.utils.rabbitmq.RabbitUtil.KT_RECEIVE_ROUTING_KEY;
import static shop.rns.smsbroker.utils.rabbitmq.RabbitUtil.RECEIVE_EXCHANGE_NAME;
=======
import static shop.rns.smsbroker.utils.rabbitmq.RabbitUtil.*;
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499

@Log4j2
@Component
@RequiredArgsConstructor
public class MessageConsumer {
    private final DlxProcessingErrorHandler dlxProcessingErrorHandler;
<<<<<<< HEAD

=======
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

<<<<<<< HEAD

    // RECEIVE
    @RabbitListener(queues = "q.sms.kt.work", concurrency = "3", ackMode = "MANUAL")
=======
    // RECEIVE
    @RabbitListener(queues = "q.sms.skt.work", concurrency = "3", ackMode = "MANUAL")
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    public void receiveMessage(Message message, Channel channel) {
        try {
            ReceiveMessageDto receiveMessageDto = objectMapper.readValue(new String(message.getBody()), ReceiveMessageDto.class);

            SmsMessageDto messageDto = receiveMessageDto.getSmsMessageDto();
<<<<<<< HEAD
            System.out.println("메시지 내용: " + messageDto.getContent());

            MessageResultDto messageResultDto = receiveMessageDto.getMessageResultDto();

//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            sendResponseToSendServer(messageResultDto);
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
=======
            log.info("메시지 내용: {}", messageDto.getContent());

            MessageResultDto messageResultDto = receiveMessageDto.getMessageResultDto();

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            sendResponseToSendServer(messageResultDto);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
        } catch (IOException e){
            log.warn("Error processing message:" + new String(message.getBody()) + ":" + e.getMessage());
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
        }
    }

    // RESPONSE TO SEND SERVER
    public void sendResponseToSendServer(final MessageResultDto messageResultDto){
        changeMassageStatusSuccess(messageResultDto);

<<<<<<< HEAD
        rabbitTemplate.convertAndSend(RECEIVE_EXCHANGE_NAME, KT_RECEIVE_ROUTING_KEY, messageResultDto);
=======
        rabbitTemplate.convertAndSend(RECEIVE_EXCHANGE_NAME, SKT_RECEIVE_ROUTING_KEY, messageResultDto);
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    }

    public MessageResultDto changeMassageStatusSuccess(MessageResultDto messageResultDto){
        messageResultDto.setMessageStatus(MessageStatus.SUCCESS);
        return messageResultDto;
    }
}
