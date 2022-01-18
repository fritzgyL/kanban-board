package rest;

import javax.ws.rs.GET;
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

}
