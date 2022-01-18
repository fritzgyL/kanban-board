package service;

import entity.Card;
import repository.CardRepository;

public class CardService implements Service<Card> {

	private CardRepository cardRepository = new CardRepository();

	@Override
	public Iterable<Card> getAll() {
		return cardRepository.findAll();
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
	public Card update(Card t) {
		// TODO Auto-generated method stub
		return null;
	}

}
