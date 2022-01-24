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

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	UserService userService = new UserService();
	BoardService boardService = new BoardService();

	@GET
	public Iterable<User> getUsers(@Context UriInfo uriInfo) {
		Iterable<User> users = userService.getAllUsers();
		users.forEach(u -> initLinks(u, uriInfo));
		return users;
	}

	@POST
	public Response addUser(User user, @Context UriInfo uriInfo) {
		User createdUser = userService.addUser(user);
		initLinks(createdUser, uriInfo);
		return Response.ok(createdUser).status(201).build();
	}

	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") long userId, User user) {
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
	public Response deleteUser(@PathParam("userId") long userId) {
		userService.removeUser(userId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId, @Context UriInfo uriInfo) {
		User user = userService.getUser(userId);
		initLinks(user, uriInfo);
		return user;
	}

//	@Path("/{userId}/boards")
//	public BoardResource getBoardResource() {
//		return new BoardResource();
//	}

	@GET
	@Path("/{userId}/boards")
	public Iterable<Board> getBoards(@PathParam("userId") long userId, @Context UriInfo uriInfo) {
		Iterable<Board> boards = boardService.getAllBoards(userId);
		boards.forEach(b -> BoardResource.initLinks(b, uriInfo));
		return boards;
	}

	@POST
	@Path("/{userId}/boards")
	public Response addBoard(@PathParam("userId") long userId, Board board, @Context UriInfo uriInfo) {
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
