package fr.istic.fritzgyl.sir.api.repository;

import javax.persistence.EntityManager;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.Tag;
import fr.istic.fritzgyl.sir.api.domain.User;

public class CardRepository extends GenericDaoJpaImpl<Card, Long> {

	public Iterable<Card> getCardsBySectionId(long sectionId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select c from card c where c.section.id=:sectionId", Card.class)
				.setParameter("sectionId", sectionId).getResultList();
	}

	public Tag saveCardTag(long cardId, Tag tag) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Card card = read(cardId);
		card.addTag(tag);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return tag;
	}

	public Iterable<User> getCardAssignees(long cardId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select a.user from assignation a where a.card.id =:cardId", User.class)
				.setParameter("cardId", cardId).getResultList();
	}

}