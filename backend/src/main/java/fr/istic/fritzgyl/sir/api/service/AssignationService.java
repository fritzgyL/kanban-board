package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Assignation;
import fr.istic.fritzgyl.sir.api.dto.AssignationPostDTO;
import fr.istic.fritzgyl.sir.api.repository.AssignationRepository;

public class AssignationService {

	private AssignationRepository assignationRepository = new AssignationRepository();

	public Iterable<Assignation> getAllAssignations() {
		return this.assignationRepository.findAll();
	}

	public Iterable<Assignation> getAllAssignationsByCardId(long cardId) {
		return this.assignationRepository.findAllByCard(cardId);
	}

	public Iterable<Assignation> getAllAssignationsByUserId(long userId) {
		return this.assignationRepository.findAllByUser(userId);
	}

	public Iterable<Assignation> getAllAssignationsByCardIdAndUserId(long cardId, long userId) {
		return this.assignationRepository.findAllByCardIdAndUserId(cardId, userId);
	}

	public Assignation addAssignation(AssignationPostDTO assignation) {
		System.out.println(assignation);
		return this.assignationRepository.addAssignation(assignation.getCardId(), assignation.getUserId());
	}

	public void removeAssignation(long assignationId) {
		this.assignationRepository.deleteAssignation(assignationId);
	}

}
