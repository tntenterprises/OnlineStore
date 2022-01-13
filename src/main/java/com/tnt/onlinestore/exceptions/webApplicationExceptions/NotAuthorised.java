package com.tnt.onlinestore.exceptions.webApplicationExceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotAuthorised extends WebApplicationException {

    public NotAuthorised(String errorMessage) {
        super(Response.status(Response
                .Status.FORBIDDEN)
                .entity(new ResponseMessage(errorMessage))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}
