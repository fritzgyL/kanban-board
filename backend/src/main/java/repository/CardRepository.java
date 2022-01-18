package repository;

import entity.Card;
import entity.Tag;

public class CardRepository extends GenericDaoJpaImpl<Card, Long> {

	public Tag addTag(long id, Tag tag) {
		entityManager.getTransaction().begin();
		Card card = read(id);
		card.addTag(tag);
		entityManager.getTransaction().commit();
		return tag;
	}

}