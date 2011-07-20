package com.dynamo.cr.server.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Map a web application exception to a message or the error code when no message exists.</p>
 */
@Provider
public class WebApplicationExceptionMapper implements
        ExceptionMapper<WebApplicationException> {

    protected static Logger logger = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

    @Override
    public Response toResponse(WebApplicationException e) {
        Response r = e.getResponse();
        if (r.getEntity() == null) {
            String message = String.format("Error %d", r.getStatus());
            logger.error(message, e);
            return Response.fromResponse(r).entity(message).build();
        } else {
            logger.error(e.getMessage(), e);
            return r;
        }
    }

}
