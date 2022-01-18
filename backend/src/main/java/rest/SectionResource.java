package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entity.Board;
import entity.Card;
import entity.Section;
import service.BoardService;
import service.CardService;
import service.SectionService;

@Path("/sections")
@Produces({ "application/json", "application/xml" })
public class SectionResource {

	SectionService sectionService = new SectionService();
	CardService cardService = new CardService();

	@DELETE
	@Path("/{id}")
	public void deleteSection(@PathParam("id") long id) {
		sectionService.delete(id);
	}

	@POST
	@Path("/{id}/cards")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Card addCard(@PathParam("id") long id, Card card) {
		cardService.save(card);
		return sectionService.addCard(id, card);
	}
}
