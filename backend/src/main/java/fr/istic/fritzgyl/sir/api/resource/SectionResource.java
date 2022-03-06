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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/sections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SectionResource {

	SectionService sectionService = new SectionService();
	CardService cardService = new CardService();

	@PUT
	@Path("/{sectionId}")
	@Operation(summary = "Replaces a section resource", tags = { "Sections" }, responses = {
			@ApiResponse(responseCode = "200", description = "Section resource updated", content = @Content(schema = @Schema(implementation = Section.class))),
			@ApiResponse(responseCode = "404", description = "Section not found") })
	public Section updateSection(@Parameter(required = true) @PathParam("sectionId") long sectionId,
			@Parameter(description = "The updated section resource", schema = @Schema(implementation = Section.class), required = true) Section section) {
		Section currentSection = sectionService.getSection(sectionId);
		if (currentSection != null) {
			String title = section.getTitle();
			if (title != null) {
				currentSection.setTitle(title);
			}
			return sectionService.updateSection(currentSection);
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{sectionId}")
	@Operation(summary = "Removes the section resource", tags = { "Sections" }, responses = {
			@ApiResponse(responseCode = "204", description = "Section resource deleted"),
			@ApiResponse(responseCode = "404", description = "Section not found") })
	public Response deleteSection(@Parameter(required = true) @PathParam("sectionId") long sectionId) {
		sectionService.removeSection(sectionId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{sectionId}")
	@Operation(summary = "Retrieves a section resource", tags = { "Sections" }, responses = {
			@ApiResponse(responseCode = "200", description = "Section resource response", content = @Content(schema = @Schema(implementation = Section.class))),
			@ApiResponse(responseCode = "404", description = "Section not found") })
	public Section getSection(@Parameter(required = true) @PathParam("sectionId") long sectionId,
			@Context UriInfo uriInfo) {
		Section section = sectionService.getSection(sectionId);
		if (section != null) {
			initLinks(section, uriInfo);
		}
		return section;
	}

	@GET
	@Path("/{sectionId}/cards")
	@Operation(summary = "Retrieves the collection of card resources", tags = { "Cards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Card collection response", content = @Content(schema = @Schema(implementation = Card.class))),
			@ApiResponse(responseCode = "404", description = "Section not found") })
	public Iterable<Card> getCards(@Parameter(required = true) @PathParam("sectionId") long sectionId,
			@Context UriInfo uriInfo) {
		Iterable<Card> cards = cardService.getAllCards(sectionId);
		cards.forEach(b -> CardResource.initLinks(b, uriInfo));
		return cards;
	}

	@POST
	@Path("/{sectionId}/cards")
	@Operation(summary = "Creates a card resource", tags = { "Cards" }, responses = {
			@ApiResponse(responseCode = "201", description = "Card resource created", content = @Content(schema = @Schema(implementation = Card.class))),
			@ApiResponse(responseCode = "404", description = "Section not found") })
	public Response addCard(@Parameter(required = true) @PathParam("sectionId") long sectionId,
			@Parameter(description = "The new card resource", schema = @Schema(implementation = Card.class), required = true) Card card,
			@Context UriInfo uriInfo) {
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