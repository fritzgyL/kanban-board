package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.User;
import fr.istic.fritzgyl.sir.api.repository.CardRepository;
import fr.istic.fritzgyl.sir.api.repository.SectionRepository;

public class CardService {

	private CardRepository cardRepository = new CardRepository();
	private SectionRepository sectionRepository = new SectionRepository();

	public Iterable<Card> getAllCards(long sectionId) {
		return cardRepository.getCardsBySectionId(sectionId);
	}

	public Card getCard(long cardId) {
		return cardRepository.read(cardId);
	}

	public Card addCard(long sectionId, Card card) {
		return sectionRepository.saveSectionCard(sectionId, card);
	}

	public void removeCard(long cardId) {
		Card card = getCard(cardId);
		card.getSection().removeCard(card);
		cardRepository.delete(card);
	}

	public Card updateCard(Card card) {
		return cardRepository.update(card);
	}

	public Iterable<User> getCardAssignees(long cardId) {
		return cardRepository.getCardAssignees(cardId);
	}

}
