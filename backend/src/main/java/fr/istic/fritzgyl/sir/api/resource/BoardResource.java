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
import fr.istic.fritzgyl.sir.api.domain.Section;
import fr.istic.fritzgyl.sir.api.service.BoardService;
import fr.istic.fritzgyl.sir.api.service.SectionService;

@Path("/api/boards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardResource {

	BoardService boardService = new BoardService();
	SectionService sectionService = new SectionService();

	@GET
	public Iterable<Board> getBoards(@PathParam("userId") long userId, @Context UriInfo uriInfo) {
		Iterable<Board> boards = boardService.getAllBoards(userId);
		boards.forEach(b -> initLinks(b, uriInfo));
		return boards;
	}

	@POST
	public Response addBoard(@PathParam("userId") long userId, Board board, @Context UriInfo uriInfo) {
		Board createdBoard = boardService.addBoard(userId, board);
		initLinks(createdBoard, uriInfo);
		return Response.ok(createdBoard).status(201).build();
	}

	@PUT
	@Path("/{boardId}")
	public Board updateBoard(@PathParam("boardId") long boardId, Board board) {
		Board currentBoard = boardService.getBoard(boardId);
		if (currentBoard != null) {
			String title = board.getTitle();
			if (title != null) {
				currentBoard.setTitle(title);
			}
			return boardService.updateBoard(currentBoard);
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{boardId}")
	public Response deleteBoard(@PathParam("boardId") long boardId) {
		boardService.removeBoard(boardId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{boardId}")
	public Board getBoard(@PathParam("boardId") long boardId, @Context UriInfo uriInfo) {
		Board board = boardService.getBoard(boardId);
		initLinks(board, uriInfo);
		return board;
	}

	@GET
	@Path("/{boardId}/sections")
	public Iterable<Section> getSections(@PathParam("boardId") long boardId, @Context UriInfo uriInfo) {
		Iterable<Section> sections = sectionService.getAllSections(boardId);
		sections.forEach(b -> SectionResource.initLinks(b, uriInfo));
		return sections;
	}

	@POST
	@Path("/{boardId}/sections")
	public Response addSection(@PathParam("boardId") long boardId, Section section, @Context UriInfo uriInfo) {
		Section createdsection = sectionService.addSection(boardId, section);
		SectionResource.initLinks(createdsection, uriInfo);
		return Response.ok(createdsection).status(201).build();
	}

	public static void initLinks(Board board, UriInfo uriInfo) {
		board.addLink(getUriForSelf(uriInfo, board), "self");
		board.addLink(getUriForUser(uriInfo, board), "user");
		board.addLink(getUriForSections(uriInfo, board), "sections");

	}

	private static String getUriForSections(UriInfo uriInfo, Board board) {
		URI uri = uriInfo.getBaseUriBuilder().path(BoardResource.class).path(BoardResource.class, "getSections")
				.resolveTemplate("boardId", board.getId()).build();
		return uri.toString();
	}

	private static String getUriForSelf(UriInfo uriInfo, Board board) {
		String uri = uriInfo.getBaseUriBuilder().path(BoardResource.class).path(Long.toString(board.getId())).build()
				.toString();
		return uri;
	}

	private static String getUriForUser(UriInfo uriInfo, Board board) {
		String uri = uriInfo.getBaseUriBuilder().path(UserResource.class).path(UserResource.class, "getUser")
				.resolveTemplate("userId", board.getUser().getId()).build().toString();
		return uri;
	}

}
