package shop.rns.smsbroker.dto.message;

import lombok.*;
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
import shop.rns.smsbroker.config.status.MessageStatus;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageResultDto {
<<<<<<< HEAD
    private String rMessageResultId;

=======
>>>>>>> 52be93bf182c2cc368e26abb2c19edc51614f499
    private long messageId;

    private long brokerId;

    private long contactId;

    private MessageStatus messageStatus;

    private LocalDateTime createdAt;
}
