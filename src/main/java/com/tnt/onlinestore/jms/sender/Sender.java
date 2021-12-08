package com.tnt.onlinestore.jms.sender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sender {

    /*JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending message");
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "TNT is ONLINE!!! Give us all your money."
                , LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.TNT_QUEUE, messageObject);
        System.out.println("Message sent");
    }*/

}
