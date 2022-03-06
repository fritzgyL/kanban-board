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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

	CardService cardService = new CardService();
	TagService tagService = new TagService();

	@PUT
	@Path("/{id}")
	@Operation(summary = "Replaces a tag resource", tags = { "Tags" }, responses = {
			@ApiResponse(responseCode = "200", description = "Tag resource updated", content = @Content(schema = @Schema(implementation = Tag.class))),
			@ApiResponse(responseCode = "404", description = "Tag not found") })
	public Tag updateTag(@Parameter(required = true) @PathParam("id") long tagId,
			@Parameter(description = "The updated tag resource", schema = @Schema(implementation = Tag.class), required = true) Tag tag) {
		Tag currentTag = tagService.getTag(tagId);
		if (currentTag != null) {
			String title = tag.getTitle();
			if (title != null) {
				currentTag.setTitle(title);
			}
			String color = tag.getColor();
			if (color != null) {
				currentTag.setColor(color);
			}
			return tagService.updateTag(currentTag);
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{tagId}")
	@Operation(summary = "Removes the tag resource", tags = { "Tags" }, responses = {
			@ApiResponse(responseCode = "204", description = "Tag resource deleted"),
			@ApiResponse(responseCode = "404", description = "Tag not found") })
	public Response deleteTag(@Parameter(required = true) @PathParam("tagId") long tagId) {
		tagService.removeTag(tagId);
		return Response.status(204).build();
	}

	@GET
	@Path("/{tagId}")
	@Operation(summary = "Retrieves a tag resource", tags = { "Tags" }, responses = {
			@ApiResponse(responseCode = "200", description = "Tag resource response", content = @Content(schema = @Schema(implementation = Tag.class))),
			@ApiResponse(responseCode = "404", description = "Tag not found") })
	public Tag getTag(@Parameter(required = true) @PathParam("tagId") long tagId, @Context UriInfo uriInfo) {
		Tag tag = tagService.getTag(tagId);
		initLinks(tag, uriInfo);
		return tag;
	}

	public static void initLinks(Tag tag, UriInfo uriInfo) {
		tag.addLink(getUriForSelf(uriInfo, tag), "self");

	}

	private static String getUriForSelf(UriInfo uriInfo, Tag tag) {
		String uri = uriInfo.getBaseUriBuilder().path(TagResource.class).path(TagResource.class, "getTag")
				.resolveTemplate("tagId", tag.getId()).build().toString();
		return uri;
	}

}
