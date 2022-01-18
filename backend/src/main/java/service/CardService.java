package service;

import entity.Card;
import entity.Tag;
import repository.CardRepository;

public class CardService implements Service<Card> {

	private CardRepository cardRepository = new CardRepository();

	@Override
	public Iterable<Card> getAll() {
		return null;
	}

	@Override
	public Card getById(long id) {
		return cardRepository.read(id);
	}

	@Override
	public Card save(Card card) {
		return cardRepository.save(card);
	}

	@Override
	public void delete(long id) {
		cardRepository.delete(getById(id));
	}

	@Override
	public Card update(Card card) {
		return cardRepository.update(card);
	}

	public Tag addTag(Long id, Tag tag) {
		return cardRepository.addTag(id, tag);
	}

}
