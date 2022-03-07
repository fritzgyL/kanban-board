package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.Section;
import fr.istic.fritzgyl.sir.api.exception.DataNotFoundException;
import fr.istic.fritzgyl.sir.api.repository.BoardRepository;
import fr.istic.fritzgyl.sir.api.repository.SectionRepository;

public class SectionService {
	private SectionRepository sectionRepository = new SectionRepository();
	private BoardRepository boardRepository = new BoardRepository();

	public Iterable<Section> getAllSections(long boardId) {
		return sectionRepository.getSectionsByBoardId(boardId);
	}

	public Section getSection(long id) {
		Section section = sectionRepository.read(id);
		if (section == null) {
			throw new DataNotFoundException("Section with id " + id + " not found");
		}
		return section;
	}

	public Section addSection(long boardId, Section section) {
		return boardRepository.saveBoardSection(boardId, section);

	}

	public Section updateSection(Section section) {
		return sectionRepository.update(section);
	}

	public void removeSection(long sectionId) {
		sectionRepository.delete(getSection(sectionId));
	}

	public Card addCard(long id, Card card) {
		return sectionRepository.addCard(id, card);
	}
}
