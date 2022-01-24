package fr.istic.fritzgyl.sir.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import fr.istic.fritzgyl.sir.api.domain.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(Status.NOT_FOUND, exception.getMessage(),
				"No data in the database has matched the id");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
