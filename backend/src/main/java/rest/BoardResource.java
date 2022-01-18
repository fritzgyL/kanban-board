package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.BoardService;
import service.SectionService;
import entity.Board;
import entity.Section;

@Path("/boards")
@Produces({ "application/json", "application/xml" })
public class BoardResource {

	BoardService boardService = new BoardService();
	SectionService sectionService = new SectionService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Board> getBoards() {
		return boardService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoardById(@PathParam("id") long id) {
		return boardService.getById(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveBoard(Board board) {
		return Response.ok(boardService.save(board)).status(201).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteBoard(@PathParam("id") long id) {
		boardService.delete(id);
		return Response.status(204).build();
	}

	@POST
	@Path("/{id}/section")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSection(@PathParam("id") long id, Section section) {
		sectionService.save(section);
		return Response.ok(boardService.addSection(id, section)).status(201).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("id") long id, Board board) {
		Board currentBoard = boardService.getById(id);
		if (currentBoard != null) {
			String title = board.getTitle();
			if (title != null) {
				currentBoard.setTitle(title);
			}
			boardService.update(currentBoard);
			return currentBoard;
		} else {
			return null;
		}
	}
}
