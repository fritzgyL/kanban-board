package fr.istic.fritzgyl.sir.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import fr.istic.fritzgyl.sir.api.domain.Assignation;
import fr.istic.fritzgyl.sir.api.dto.AssignationGetDTO;

@Mapper
public interface AssignationMapper {
	AssignationMapper INSTANCE = Mappers.getMapper(AssignationMapper.class);

	@Mappings({ @Mapping(target = "assigneeFirstName", source = "assignation.user.firstName"),
			@Mapping(target = "assigneeLastName", source = "assignation.user.lastName"),
			@Mapping(target = "id", source = "assignation.id"), })
	AssignationGetDTO assignationToAssignationDTO(Assignation assignation);
}
