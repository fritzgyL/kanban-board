package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.BoardService;
import service.SectionService;
import entity.Board;
import entity.Card;
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
	public Board saveBoard(Board board) {
		return boardService.save(board);
	}

	@DELETE
	@Path("/{id}")
	public void deleteBoard(@PathParam("id") long id) {
		System.out.println(id);
		boardService.delete(id);
	}

	@POST
	@Path("/{id}/card")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addSection(@PathParam("id") long id, Section section) {
		sectionService.save(section);
		return boardService.addSection(id, section);
	}
}
