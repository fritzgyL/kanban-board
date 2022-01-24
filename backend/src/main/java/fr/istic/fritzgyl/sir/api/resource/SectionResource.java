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

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.Section;
import fr.istic.fritzgyl.sir.api.service.CardService;
import fr.istic.fritzgyl.sir.api.service.SectionService;

@Path("/api/sections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SectionResource {

	SectionService sectionService = new SectionService();
	CardService cardService = new CardService();

	@PUT
	@Path("/{sectionId}")
	public Section updateSection(@PathParam("sectionId") long sectionId, Section section) {
		Section currentSection = sectionService.getSection(sectionId);
		if (currentSection != null) {
			String title = section.getTitle();
			if (title != null) {
				currentSection.setTitle(title);
			}
			sectionService.updateSection(currentSection);
			return currentSection;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{sectionId}")
	public Response deleteSection(@PathParam("sectionId") long sectionId) {
		sectionService.removeSection(sectionId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{sectionId}")
	public Section getSection(@PathParam("sectionId") long sectionId, @Context UriInfo uriInfo) {
		Section section = sectionService.getSection(sectionId);
		initLinks(section, uriInfo);
		return section;
	}

	@GET
	@Path("/{sectionId}/cards")
	public Iterable<Card> getCards(@PathParam("sectionId") long sectionId, @Context UriInfo uriInfo) {
		Iterable<Card> cards = cardService.getAllCards(sectionId);
		cards.forEach(b -> CardResource.initLinks(b, uriInfo));
		return cards;
	}

	@POST
	@Path("/{sectionId}/cards")
	public Response addCard(@PathParam("sectionId") long sectionId, Card card, @Context UriInfo uriInfo) {
		Card createdcard = cardService.addCard(sectionId, card);
		CardResource.initLinks(createdcard, uriInfo);
		return Response.ok(createdcard).status(201).build();
	}

	public static void initLinks(Section section, UriInfo uriInfo) {
		section.addLink(getUriForSelf(uriInfo, section), "self");
		section.addLink(getUriForBoard(uriInfo, section), "board");
		section.addLink(getUriForCards(uriInfo, section), "cards");

	}

	private static String getUriForCards(UriInfo uriInfo, Section section) {
		URI uri = uriInfo.getBaseUriBuilder().path(SectionResource.class).path(SectionResource.class, "getCards")
				.resolveTemplate("sectionId", section.getId()).build();
		return uri.toString();
	}

	private static String getUriForSelf(UriInfo uriInfo, Section section) {
		String uri = uriInfo.getBaseUriBuilder().path(SectionResource.class).path(SectionResource.class, "getSection")
				.resolveTemplate("sectionId", section.getId()).build().toString();
		return uri;
	}

	private static String getUriForBoard(UriInfo uriInfo, Section section) {
		String uri = uriInfo.getBaseUriBuilder().path(BoardResource.class).path(BoardResource.class, "getBoard")
				.resolveTemplate("boardId", section.getBoard().getId()).build().toString();
		return uri;
	}

}
