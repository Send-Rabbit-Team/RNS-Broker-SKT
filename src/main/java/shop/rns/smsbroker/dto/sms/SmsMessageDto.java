package shop.rns.smsbroker.dto.sms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.Setter;
import shop.rns.smsbroker.config.status.MessageStatus;
import shop.rns.smsbroker.config.type.MessageType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
=======
import shop.rns.smsbroker.config.status.MessageStatus;
import shop.rns.smsbroker.config.type.MessageType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
public class SmsMessageDto {
    private long messageId;

    private String from;

    @JsonIgnore
    private String to;

    private String subject;

    private String content;

    private String image;

    private MessageStatus messageStatus;

    private MessageType messageType;

    @JsonIgnore
    private String reserveTime;

    @JsonIgnore
    private String scheduleCode;
}
