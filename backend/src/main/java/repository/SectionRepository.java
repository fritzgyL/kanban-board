package repository;

import entity.Card;
import entity.Section;

public class SectionRepository extends GenericDaoJpaImpl<Section, Long> {

	public Card addCard(long id, Card card) {
		entityManager.getTransaction().begin();
		Section section = read(id);
		section.addCard(card);
		entityManager.getTransaction().commit();
		return card;
	}

}