package com.tnt.onlinestore.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthorisationInvalid extends WebApplicationException {

    public AuthorisationInvalid(String errorMessage) {
        super(Response.status(Response
                .Status.FORBIDDEN)
                .entity(new ResponseMessage(errorMessage))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}
