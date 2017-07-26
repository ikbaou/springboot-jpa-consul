package com.example.demo.rest.response.mapper;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        if (exception instanceof ConstraintDefinitionException) {
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if (exception instanceof ConstraintDeclarationException) {
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if (exception instanceof GroupDefinitionException) {
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if(exception instanceof ConstraintViolationException){
          return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.BAD_REQUEST);

        }
//        if (exception instanceof ResteasyViolationException) {
//            ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(exception);
//            Exception e = resteasyViolationException.getException();
//            if (e != null) {
//                return buildResponse(unwrapException(e), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
//            } else if (resteasyViolationException.getReturnValueViolations().size() == 0) {
//                return buildViolationReportResponse(resteasyViolationException, Status.BAD_REQUEST);
//            } else {
//                return buildViolationReportResponse(resteasyViolationException, Status.INTERNAL_SERVER_ERROR);
//            }
//        }
        return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
    }

    protected Response buildResponse(Object entity, String mediaType, Status status) {
        ResponseBuilder builder = Response.status(status).entity(entity);
        builder.type(MediaType.TEXT_PLAIN);
        builder.header("validation-failed", "true");
        return builder.build();
    }

//    protected Response buildViolationReportResponse(ResteasyViolationException exception, Status status) {
//        ResponseBuilder builder = Response.status(status);
//        builder.header(Validation.VALIDATION_HEADER, "true");
//
//        // Check standard media types.
//        MediaType mediaType = getAcceptMediaType(exception.getAccept());
//        if (mediaType != null) {
//            builder.type(mediaType);
//            builder.entity(new ViolationReport(exception));
//            return builder.build();
//        }
//
//        // Default media type.
//        builder.type(MediaType.TEXT_PLAIN);
//        builder.entity(exception.toString());
//        return builder.build();
//    }

    protected String unwrapException(Throwable t) {
        StringBuffer sb = new StringBuffer();
        doUnwrapException(sb, t);
        return sb.toString();
    }

    private void doUnwrapException(StringBuffer sb, Throwable t) {
        if (t == null) {
            return;
        }
        sb.append(t.toString());
        if (t.getCause() != null && t != t.getCause()) {
            sb.append('[');
            doUnwrapException(sb, t.getCause());
            sb.append(']');
        }
    }

    @SuppressWarnings("unused")
	private MediaType getAcceptMediaType(List<MediaType> accept) {
        Iterator<MediaType> it = accept.iterator();
        while (it.hasNext()) {
            MediaType mt = it.next();
            /*
             * application/xml media type causes an exception:
             * org.jboss.resteasy.core.NoMessageBodyWriterFoundFailure: Could not find MessageBodyWriter for response
             * object of type: org.jboss.resteasy.api.validation.ViolationReport of media type: application/xml
             */
            /*if (MediaType.APPLICATION_XML_TYPE.getType().equals(mt.getType())
                    && MediaType.APPLICATION_XML_TYPE.getSubtype().equals(mt.getSubtype())) {
                return MediaType.APPLICATION_XML_TYPE;
            }*/
            if (MediaType.APPLICATION_JSON_TYPE.getType().equals(mt.getType())
                    && MediaType.APPLICATION_JSON_TYPE.getSubtype().equals(mt.getSubtype())) {
                return MediaType.APPLICATION_JSON_TYPE;
            }
        }
        return null;
    }
}
