
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
import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.service.BoardService;
import fr.istic.fritzgyl.sir.api.service.SectionService;
import fr.istic.fritzgyl.sir.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/boards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardResource {

	BoardService boardService = new BoardService();
	SectionService sectionService = new SectionService();
	TagService tagService = new TagService();

	@PUT
	@Path("/{boardId}")
	@Operation(summary = "Replaces a board resource", tags = { "Boards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Board resource updated", content = @Content(schema = @Schema(implementation = Board.class))),
			@ApiResponse(responseCode = "404", description = "Board not found") })
	public Board updateBoard(@Parameter(required = true) @PathParam("boardId") long boardId,
			@Parameter(description = "The updated user resource", schema = @Schema(implementation = User.class), required = true) Board board) {
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
	@Operation(summary = "Removes the board resource", tags = { "Boards" }, responses = {
			@ApiResponse(responseCode = "204", description = "Board resource deleted"),
			@ApiResponse(responseCode = "404", description = "Board not found") })
	public Response deleteBoard(@Parameter(required = true) @PathParam("boardId") long boardId) {
		boardService.removeBoard(boardId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{boardId}")
	@Operation(summary = "Retrieves a board resource", tags = { "Boards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Board resource response", content = @Content(schema = @Schema(implementation = Board.class))),
			@ApiResponse(responseCode = "404", description = "Board not found") })
	public Board getBoard(@Parameter(required = true) @PathParam("boardId") long boardId, @Context UriInfo uriInfo) {
		Board board = boardService.getBoard(boardId);
		if (board != null) {
			initLinks(board, uriInfo);
		}
		return board;
	}

	@GET
	@Path("/{boardId}/sections")
	@Operation(summary = "Retrieves the collection of section resources", tags = { "Sections" }, responses = {
			@ApiResponse(responseCode = "200", description = "Section collection response", content = @Content(schema = @Schema(implementation = Section.class))),
			@ApiResponse(responseCode = "404", description = "Board not found") })
	public Iterable<Section> getSections(@Parameter(required = true) @PathParam("boardId") long boardId,
			@Context UriInfo uriInfo) {
		Iterable<Section> sections = sectionService.getAllSections(boardId);
		sections.forEach(b -> SectionResource.initLinks(b, uriInfo));
		return sections;
	}

	@POST
	@Path("/{boardId}/sections")
	@Operation(summary = "Creates a section resource", tags = { "Sections" }, responses = {
			@ApiResponse(responseCode = "201", description = "Section resource created", content = @Content(schema = @Schema(implementation = Section.class))),
			@ApiResponse(responseCode = "404", description = "Board not found") })
	public Response addSection(@Parameter(required = true) @PathParam("boardId") long boardId,
			@Parameter(description = "The new section resource", schema = @Schema(implementation = Section.class), required = true) Section section,
			@Context UriInfo uriInfo) {
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
