package service;

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
		return sectionRepository.read(id);
	}

	@Override
	public Section save(Section section) {
		return sectionRepository.save(section);

	}

	@Override
	public void delete(long id) {
		sectionRepository.delete(getById(id));
	}

	@Override
	public Section update(Section section) {
		return sectionRepository.update(section);
	}

	public Card addCard(long id, Card card) {
		return sectionRepository.addCard(id, card);
	}
}
