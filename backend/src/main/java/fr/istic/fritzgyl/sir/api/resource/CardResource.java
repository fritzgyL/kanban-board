package fr.istic.fritzgyl.sir.api.resource;

import java.net.URI;
import java.sql.Date;

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
import fr.istic.fritzgyl.sir.api.domain.Tag;
import fr.istic.fritzgyl.sir.api.service.CardService;
import fr.istic.fritzgyl.sir.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {

	CardService cardService = new CardService();
	TagService tagService = new TagService();

	@PUT
	@Path("/{cardId}")
	@Operation(summary = "Replaces a card resource", tags = { "Cards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Card resource updated", content = @Content(schema = @Schema(implementation = Card.class))) })
	public Card updateCard(@Parameter(required = true) @PathParam("cardId") long cardId,
			@Parameter(description = "The updated card resource", schema = @Schema(implementation = Card.class), required = true) Card card) {
		Card currentCard = cardService.getCard(cardId);
		if (currentCard != null) {
			String title = card.getTitle();
			if (title != null) {
				currentCard.setTitle(title);
			}
			Date deadline = card.getDeadline();
			if (deadline != null) {
				currentCard.setDeadline(deadline);
			}
			String description = card.getDescription();
			if (description != null) {
				currentCard.setDescription(description);
			}
			int estimatedTime = card.getEstimatedTime();
			if (estimatedTime != 0) {
				currentCard.setEstimatedTime(estimatedTime);
			}
			String location = card.getLocation();
			if (location != null) {
				currentCard.setLocation(location);
			}
			String url = card.getUrl();
			if (url != null) {
				currentCard.setUrl(url);
			}
			return cardService.updateCard(currentCard);
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{cardId}")
	@Operation(summary = "Removes the card resource", tags = { "Cards" }, responses = {
			@ApiResponse(responseCode = "204", description = "Card resource deleted") })
	public Response deleteCard(@Parameter(required = true) @PathParam("cardId") long cardId) {
		cardService.removeCard(cardId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{cardId}")
	@Operation(summary = "Retrieves a card resource", tags = { "Cards" }, responses = {
			@ApiResponse(responseCode = "200", description = "Card resource response", content = @Content(schema = @Schema(implementation = Card.class))) })
	public Card getCard(@Parameter(required = true) @PathParam("cardId") long cardId, @Context UriInfo uriInfo) {
		Card card = cardService.getCard(cardId);
		initLinks(card, uriInfo);
		return card;
	}

	@GET
	@Path("/{cardId}/tags")
	@Operation(summary = "Retrieves the collection of tag resources", tags = { "Tags" }, responses = {
			@ApiResponse(responseCode = "200", description = "Tag collection response", content = @Content(schema = @Schema(implementation = Tag.class))) })
	public Iterable<Tag> getTags(@Parameter(required = true) @PathParam("cardId") long cardId,
			@Context UriInfo uriInfo) {
		Iterable<Tag> tags = tagService.getAllTags(cardId);
		tags.forEach(b -> TagResource.initLinks(b, uriInfo));
		return tags;
	}

	@POST
	@Path("/{cardId}/tags")
	@Operation(summary = "Creates a tag resource", tags = { "Tags" }, responses = {
			@ApiResponse(responseCode = "201", description = "Tag resource created", content = @Content(schema = @Schema(implementation = Tag.class))) })
	public Response addTag(@Parameter(required = true) @PathParam("boardId") long cardId,
			@Parameter(description = "The new tag resource", schema = @Schema(implementation = Tag.class), required = true) Tag tag,
			@Context UriInfo uriInfo) {
		Tag createdtag = tagService.addTag(cardId, tag);
		TagResource.initLinks(createdtag, uriInfo);
		return Response.ok(createdtag).status(201).build();
	}

	public static void initLinks(Card card, UriInfo uriInfo) {
		card.addLink(getUriForSelf(uriInfo, card), "self");
		card.addLink(getUriForSection(uriInfo, card), "section");
		card.addLink(getUriForTags(uriInfo, card), "tags");

	}

	private static String getUriForTags(UriInfo uriInfo, Card card) {
		URI uri = uriInfo.getBaseUriBuilder().path(CardResource.class).path(CardResource.class, "getTags")
				.resolveTemplate("cardId", card.getId()).build();
		return uri.toString();
	}

	private static String getUriForSelf(UriInfo uriInfo, Card card) {
		String uri = uriInfo.getBaseUriBuilder().path(CardResource.class).path(CardResource.class, "getCard")
				.resolveTemplate("cardId", card.getId()).build().toString();
		return uri;
	}

	private static String getUriForSection(UriInfo uriInfo, Card card) {
		String uri = uriInfo.getBaseUriBuilder().path(SectionResource.class).path(SectionResource.class, "getSection")
				.resolveTemplate("sectionId", card.getSection().getId()).build().toString();
		return uri;
	}

}