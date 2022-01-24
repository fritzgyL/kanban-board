package fr.istic.fritzgyl.sir.api.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.istic.fritzgyl.sir.api.domain.Board;
import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.service.BoardService;
import fr.istic.fritzgyl.sir.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	UserService userService = new UserService();
	BoardService boardService = new BoardService();

	@GET
	@Operation(summary = "Retrieves the collection of user resources", tags = { "Users" }, responses = {
			@ApiResponse(responseCode = "200", description = "User collection response", content = @Content(schema = @Schema(implementation = User.class))) })
	public Iterable<User> getUsers(@Context UriInfo uriInfo) {
		Iterable<User> users = userService.getAllUsers();
		users.forEach(u -> initLinks(u, uriInfo));
		return users;
	}

	@POST
	@Operation(summary = "Creates a user resource", tags = { "Users" }, responses = {
			@ApiResponse(responseCode = "201", description = "User resource created", content = @Content(schema = @Schema(implementation = User.class))) })
	public Response addUser(
			@Parameter(description = "The new user resource", schema = @Schema(implementation = User.class), required = true) User user,
			@Context UriInfo uriInfo) {
		User createdUser = userService.addUser(user);
		initLinks(createdUser, uriInfo);
		return Response.ok(createdUser).status(201).build();
	}

	@PUT
	@Path("/{userId}")
	@Operation(summary = "Replaces a user resource", tags = { "Users" }, responses = {
			@ApiResponse(responseCode = "200", description = "User resource updated", content = @Content(schema = @Schema(implementation = User.class))) })
	public User updateUser(@Parameter(required = true) @PathParam("userId") long userId,
			@Parameter(description = "The updated user resource", schema = @Schema(implementation = User.class), required = true) User user) {
		User currentUser = userService.getUser(userId);
		if (currentUser != null) {
			String firstName = user.getFirstName();
			if (firstName != null) {
				currentUser.setFirstName(firstName);
			}
			String lastName = user.getLastName();
			if (lastName != null) {
				currentUser.setLastName(lastName);
			}
			return userService.updateUser(currentUser);
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{userId}")
	@Operation(summary = "Removes the user resource", tags = { "Users" }, responses = {
			@ApiResponse(responseCode = "204", description = "User resource deleted") })
	public Response deleteUser(@Parameter(required = true) @PathParam("userId") long userId) {
		userService.removeUser(userId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{userId}")
	@Operation(summary = "Retrieves a user resource", tags = { "Users" }, responses = {
			@ApiResponse(responseCode = "200", description = "User resource response", content = @Content(schema = @Schema(implementation = User.class))) })
	public User getUser(@Parameter(required = true) @PathParam("userId") long userId, @Context UriInfo uriInfo) {
		User user = userService.getUser(userId);
		initLinks(user, uriInfo);
		return user;
	}

	@GET
	@Path("/{userId}/boards")
	@Operation(summary = "Retrieves the collection of board resources", tags = { "Boards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Board collection response", content = @Content(schema = @Schema(implementation = Board.class))) })
	public Iterable<Board> getBoards(@Parameter(required = true) @PathParam("userId") long userId,
			@Context UriInfo uriInfo) {
		Iterable<Board> boards = boardService.getAllBoards(userId);
		boards.forEach(b -> BoardResource.initLinks(b, uriInfo));
		return boards;
	}

	@POST
	@Path("/{userId}/boards")
	@Operation(summary = "Creates a board resource", tags = { "Boards" }, responses = {
			@ApiResponse(responseCode = "201", description = "Board resource created", content = @Content(schema = @Schema(implementation = Board.class))) })
	public Response addBoard(@Parameter(required = true) @PathParam("userId") long userId,
			@Parameter(description = "The new board resource", schema = @Schema(implementation = Board.class), required = true) Board board,
			@Context UriInfo uriInfo) {
		Board createdBoard = boardService.addBoard(userId, board);
		BoardResource.initLinks(createdBoard, uriInfo);
		return Response.ok(createdBoard).status(201).build();
	}

	public void initLinks(User user, UriInfo uriInfo) {
		user.addLink(getUriForSelf(uriInfo, user), "self");
		user.addLink(getUriForBoards(uriInfo, user), "boards");
	}

	private String getUriForBoards(UriInfo uriInfo, User user) {
		URI uri = uriInfo.getBaseUriBuilder().path(UserResource.class).path(UserResource.class, "getBoards")
				.resolveTemplate("userId", user.getId()).build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, User user) {
		String uri = uriInfo.getBaseUriBuilder().path(UserResource.class).path(Long.toString(user.getId())).build()
				.toString();
		return uri;
	}

}
