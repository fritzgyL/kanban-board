package fr.istic.fritzgyl.sir.api.repository;

import fr.istic.fritzgyl.sir.api.domain.Tag;

public class TagRepository extends GenericDaoJpaImpl<Tag, Long> {

	public Iterable<Tag> getTagsByCardId(long cardId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select t from tag t where t.card.id=:cardId", Tag.class).setParameter("cardId", cardId)
				.getResultList();
	}

}