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
import entity.Board;

@Path("/boards")
@Produces({ "application/json", "application/xml" })
public class BoardResource {

	BoardService service = new BoardService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Board> getBoards() {
		return service.getBoards();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoardById(@PathParam("id") long id) {
		return service.getBoardById(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board saveBoard(Board board) {
		return service.saveBoard(board);
	}

	@DELETE
	@Path("/{id}")
	public void deleteBoard(@PathParam("id") long id) {
		System.out.println(id);
		service.deleteBoard(id);
	}

}
