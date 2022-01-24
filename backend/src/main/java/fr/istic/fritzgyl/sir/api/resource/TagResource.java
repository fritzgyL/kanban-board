package fr.istic.fritzgyl.sir.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.istic.fritzgyl.sir.api.domain.Tag;
import fr.istic.fritzgyl.sir.api.service.CardService;
import fr.istic.fritzgyl.sir.api.service.TagService;

@Path("/api/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

	CardService cardService = new CardService();
	TagService tagService = new TagService();

	@PUT
	@Path("/{id}")
	public Tag updateTag(@PathParam("id") long tagId, Tag tag) {
		Tag currentTag = tagService.getTag(tagId);
		if (currentTag != null) {
			String title = tag.getTitle();
			if (title != null) {
				currentTag.setTitle(title);
			}
			tagService.updateTag(currentTag);
			return currentTag;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{tagId}")
	public Response deleteTag(@PathParam("tagId") long tagId) {
		tagService.removeTag(tagId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{tagId}")
	public Tag getCard(@PathParam("tagId") long tagId, @Context UriInfo uriInfo) {
		Tag tag = tagService.getTag(tagId);
		initLinks(tag, uriInfo);
		return tag;
	}

	public static void initLinks(Tag tag, UriInfo uriInfo) {
		tag.addLink(getUriForSelf(uriInfo, tag), "self");
		tag.addLink(getUriForCard(uriInfo, tag), "card");

	}

	private static String getUriForSelf(UriInfo uriInfo, Tag tag) {
		String uri = uriInfo.getBaseUriBuilder().path(TagResource.class).path(TagResource.class, "getTag")
				.resolveTemplate("tagId", tag.getId()).build().toString();
		return uri;
	}

	private static String getUriForCard(UriInfo uriInfo, Tag tag) {
		String uri = uriInfo.getBaseUriBuilder().path(CardResource.class).path(CardResource.class, "getCard")
				.resolveTemplate("cardId", tag.getCard().getId()).build().toString();
		return uri;
	}

}
