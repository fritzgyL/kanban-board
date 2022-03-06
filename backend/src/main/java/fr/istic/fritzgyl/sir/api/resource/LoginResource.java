package fr.istic.fritzgyl.sir.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

	private UserService userService = new UserService();

	@POST
	@Operation(summary = "Check user credentials for login", tags = { "Authentification" }, responses = {
			@ApiResponse(responseCode = "200", description = "The user resource", content = @Content(schema = @Schema(implementation = User.class))) })
	public Response signin(
			@Parameter(description = "The user resource", schema = @Schema(implementation = User.class), required = true) User user) {
		return Response.ok(userService.signin(user)).status(200).build();
	}

	@POST
	@Path("/signup")
	@Operation(summary = "User signup", tags = { "Authentification" }, responses = {
			@ApiResponse(responseCode = "201", description = "The signed up user resource", content = @Content(schema = @Schema(implementation = User.class))) })
	public Response signup(
			@Parameter(description = "The user resource", schema = @Schema(implementation = User.class), required = true) User user,
			@Context UriInfo uriInfo) {
		User mUser = userService.signup(user);
		UserResource.initLinks(mUser, uriInfo);
		return Response.ok(mUser).status(201).build();
	}
}
