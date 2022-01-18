package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import repository.BoardRepository;
import entity.Board;

@Path("/board")
@Produces({ "application/json", "application/xml" })
public class BoardResource {

	BoardRepository repository = new BoardRepository();

	@GET
	@Path("/boards")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getBoards() {
		return repository.findAll();
	}

}
