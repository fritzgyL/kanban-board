package fr.istic.fritzgyl.sir.api.repository;

import javax.persistence.EntityManager;

import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.Section;

public class SectionRepository extends GenericDaoJpaImpl<Section, Long> {

	public Card addCard(long id, Card card) {
//		entityManager.getTransaction().begin();
//		Section section = read(id);
//		section.addCard(card);;
//		entityManager.getTransaction().commit();
		return card;
	}

	public Iterable<Section> getSectionsByBoardId(long boardId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select s from section s where s.board.id=:boardId", Section.class)
				.setParameter("boardId", boardId).getResultList();
	}

	public Card saveSectionCard(long sectionId, Card card) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Section section = read(sectionId);
		section.addCard(card);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return card;
	}

}