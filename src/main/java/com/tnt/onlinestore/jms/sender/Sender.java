package com.tnt.onlinestore.jms.sender;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.tnt.onlinestore.jms.config.JmsConfig;
import com.tnt.onlinestore.jms.model.MessageObject;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Sender {

    JmsTemplate jmsTemplate;

   /* @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        System.out.println("Sending message at " + LocalDateTime.now().atZone(ZoneId.of("Europe/Stockholm")));
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "TNT is ONLINE!!! Give us all your money.", LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.TNT_QUEUE, messageObject);
        System.out.println("Message sent");
    }*/

    public void sendCustomMessage(String message) {
        System.out.println("Sending message at " + LocalDateTime.now().atZone(ZoneId.of("Europe/Stockholm")));
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), message, LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.TNT_QUEUE, messageObject);
        System.out.println("Message sent");
    }

}
