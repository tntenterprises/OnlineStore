package com.tnt.onlinestore.jms.sender;

import com.tnt.onlinestore.jms.config.JmsConfig;
import com.tnt.onlinestore.jms.model.MessageObject;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.UUID;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class Sender {

    JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        System.out.println("Sending message at " + LocalDateTime.now());
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "TNT is ONLINE!!! Give us all your money.", LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.TNT_QUEUE, messageObject);
        System.out.println("Message sent");
    }

}
