package fr.istic.fritzgyl.sir.api.repository;

import javax.persistence.EntityManager;
import fr.istic.fritzgyl.sir.api.domain.Tag;

public class TagRepository extends GenericDaoJpaImpl<Tag, Long> {

	public Iterable<Tag> getTagsByCardId(long cardId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select t from tag t where t.card.id = :cardId", Tag.class).setParameter("cardId", cardId)
				.getResultList();
	}

	public void deleteAndRemoveFromCard(long id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Tag tag = em.find(Tag.class, id);
		tag.getCard().removeTag(tag);
		em.remove(em.contains(tag) ? tag : em.merge(tag));
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
	}

}