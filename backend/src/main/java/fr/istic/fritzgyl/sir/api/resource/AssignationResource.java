
package fr.istic.fritzgyl.sir.api.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import fr.istic.fritzgyl.sir.api.AssignationMapper;
import fr.istic.fritzgyl.sir.api.domain.Assignation;
import fr.istic.fritzgyl.sir.api.dto.AssignationGetDTO;
import fr.istic.fritzgyl.sir.api.dto.AssignationPostDTO;
import fr.istic.fritzgyl.sir.api.service.AssignationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/api/v1/assignations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssignationResource {

	AssignationService assignationService = new AssignationService();

	private AssignationMapper mapper = AssignationMapper.INSTANCE;

	@GET
	@Operation(summary = "Retrieves the collection of assignation resources", tags = { "Assignations" }, responses = {
			@ApiResponse(responseCode = "200", description = "Assignation collection response", content = @Content(schema = @Schema(implementation = AssignationPostDTO.class))) })
	public Iterable<AssignationGetDTO> getAssignations(
			@Parameter(description = "Card id to filter by", required = false) @QueryParam("cardId") long cardId,
			@Parameter(description = "User id to filter by", required = false) @QueryParam("userId") long userId) {
		Iterable<Assignation> result = null;
		List<AssignationGetDTO> resultWithDTO = new ArrayList<AssignationGetDTO>();
		if (cardId > 0 && userId > 0) {
			result = assignationService.getAllAssignationsByCardIdAndUserId(cardId, userId);
		} else if (cardId > 0) {
			result = assignationService.getAllAssignationsByCardId(cardId);
		} else if (userId > 0) {
			result = assignationService.getAllAssignationsByUserId(userId);
		}
		result = assignationService.getAllAssignations();

		Iterator<Assignation> it = result.iterator();
		while (it.hasNext()) {
			resultWithDTO.add(mapper.assignationToAssignationDTO(it.next()));
		}
		return resultWithDTO;
	}

	@POST
	@Operation(summary = "Creates an assignation", tags = { "Assignations" }, responses = {
			@ApiResponse(responseCode = "201", description = "Assignation resource created", content = @Content(schema = @Schema(implementation = AssignationGetDTO.class))), })
	public Response addAssignation(
			@Parameter(description = "The new assignation resource", schema = @Schema(implementation = AssignationPostDTO.class), required = true) AssignationPostDTO assignation) {
		Assignation createdAssignation = assignationService.addAssignation(assignation);
		return Response.ok(mapper.assignationToAssignationDTO(createdAssignation)).status(201).build();
	}

	@DELETE
	@Path("/{assignationId}")
	@Operation(summary = "Delete the assignation", tags = { "Assignations" }, responses = {
			@ApiResponse(responseCode = "204", description = "Assignation resource deleted"),
			@ApiResponse(responseCode = "404", description = "Assignation not found") })
	public Response deleteAssignation(@Parameter(required = true) @PathParam("assignationId") long assignationId) {
		assignationService.removeAssignation(assignationId);
		return Response.status(204).build();
	}

}
