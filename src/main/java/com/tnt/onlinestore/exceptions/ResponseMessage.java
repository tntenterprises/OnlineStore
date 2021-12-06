package com.tnt.onlinestore.exceptions;

import javax.json.bind.annotation.JsonbProperty;

public class ResponseMessage {

    private final String response;

    public ResponseMessage(String response) {
        this.response = response;
    }

    @JsonbProperty("response")
    public String getResponse() {
        return response;
    }

}
