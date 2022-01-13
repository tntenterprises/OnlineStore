package com.tnt.onlinestore.jms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter  @NoArgsConstructor @AllArgsConstructor
public class MessageObject implements Serializable {

    private UUID id;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH.mm:ss")
    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "MessageObject{" +
                "id: " + id +
                ", message: " + message + '\'' +
                ", timestamp: " + localDateTime +
                '}';
    }
}
