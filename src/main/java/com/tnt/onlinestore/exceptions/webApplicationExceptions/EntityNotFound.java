package com.tnt.onlinestore.exceptions.webApplicationExceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EntityNotFound extends WebApplicationException {

    public EntityNotFound(String errorMessage) {
        super(Response.status(Response
                .Status.NOT_FOUND)
                .entity(new ResponseMessage(errorMessage))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}
