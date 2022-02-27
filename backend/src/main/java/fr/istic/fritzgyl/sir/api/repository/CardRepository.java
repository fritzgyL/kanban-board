package fr.istic.fritzgyl.sir.api.repository;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.Tag;

public class CardRepository extends GenericDaoJpaImpl<Card, Long> {

	public Iterable<Card> getCardsBySectionId(long sectionId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select c from card c where c.section.id=:sectionId", Card.class)
				.setParameter("sectionId", sectionId).getResultList();
	}

	public Tag saveCardTag(long cardId, Tag tag) {
//		entityManager.getTransaction().begin();
//		Card card = read(cardId);
//		card.addTag(tag);;
//		entityManager.getTransaction().commit();
		return tag;
	}
	

}