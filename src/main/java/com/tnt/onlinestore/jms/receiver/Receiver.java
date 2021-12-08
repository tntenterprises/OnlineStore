package com.tnt.onlinestore.jms.receiver;

import com.tnt.onlinestore.jms.config.JmsConfig;
import com.tnt.onlinestore.jms.model.MessageObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = JmsConfig.TNT_QUEUE)
    public void listen(@Payload MessageObject messageObject) {
        System.out.println("A message was received.");
        System.out.println(messageObject);
    }

}
