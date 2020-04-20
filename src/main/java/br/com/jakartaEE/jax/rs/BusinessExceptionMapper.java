package br.com.jakartaEE.jax.rs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.jakartaEE.exceptions.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).type("text/plain").build();
	}

}
