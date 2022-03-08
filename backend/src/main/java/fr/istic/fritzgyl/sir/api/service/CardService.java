package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.exception.DataNotFoundException;
import fr.istic.fritzgyl.sir.api.repository.CardRepository;
import fr.istic.fritzgyl.sir.api.repository.SectionRepository;

public class CardService {

	private CardRepository cardRepository = new CardRepository();
	private SectionRepository sectionRepository = new SectionRepository();

	public Iterable<Card> getAllCards(long sectionId) {
		return cardRepository.getCardsBySectionId(sectionId);
	}

	public Card getCard(long cardId) {
		Card card = cardRepository.read(cardId);
		if (card == null) {
			throw new DataNotFoundException("Card with id " + cardId + " not found");
		}
		return card;
	}

	public Card addCard(long sectionId, Card card) {
		return sectionRepository.saveSectionCard(sectionId, card);
	}

	public void removeCard(long cardId) {
		cardRepository.deleteAndRemoveFromSection(cardId);
	}

	public Card updateCard(Card card) {
		return cardRepository.update(card);
	}

}
