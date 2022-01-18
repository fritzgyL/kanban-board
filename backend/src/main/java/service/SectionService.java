package service;

import entity.Board;
import entity.Card;
import entity.Section;
import repository.SectionRepository;

public class SectionService implements Service<Section> {
	private SectionRepository sectionRepository = new SectionRepository();

	@Override
	public Iterable<Section> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section save(Section section) {
		return sectionRepository.save(section);

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Section update(Section t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Card addCard(long id, Card card) {
		return sectionRepository.addCard(id, card);
	}
}
