package fr.istic.fritzgyl.sir.api.repository;

import java.util.List;

import javax.persistence.EntityManager;

import fr.istic.fritzgyl.sir.api.domain.Assignation;
import fr.istic.fritzgyl.sir.api.domain.Card;
import fr.istic.fritzgyl.sir.api.domain.User;

public class AssignationRepository extends GenericDaoJpaImpl<Assignation, Long> {

	private UserRepository userRepository = new UserRepository();
	private CardRepository cardRepository = new CardRepository();

	public Assignation addAssignation(long cardId, long userId) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		User user = this.userRepository.read(userId);
		Card card = this.cardRepository.read(cardId);
		Assignation assignation = null;
		if (user != null && card != null) {
			assignation = new Assignation();
			user.addAssignation(assignation);
			card.addAssignation(assignation);
		}
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return assignation;
	}

	public List<Assignation> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select a from assignation a", Assignation.class)
				.getResultList();
	}

	public List<Assignation> findAllByCard(long cardId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select a from assignation a where a.card.id =: cardId", Assignation.class)
				.setParameter("cardId", cardId).getResultList();
	}

	public List<Assignation> findAllByUser(long userId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select a from assignation a where a.user.id =: userId", Assignation.class)
				.setParameter("userId", userId).getResultList();
	}

	public Iterable<Assignation> findAllByCardIdAndUserId(long cardId, long userId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select a from assignation a where a.user.id =: userId and a.card.id =: cardId",
						Assignation.class)
				.setParameter("userId", userId).setParameter("cardId", cardId).getResultList();
	}

	public void deleteAssignation(long id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Assignation assignation = em.find(Assignation.class, id);
		this.syncAssignationWithUser(assignation.getUser(), assignation);
		this.syncAssignationWithCard(assignation.getCard(), assignation);
		em.remove(em.contains(assignation) ? assignation : em.merge(assignation));
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
	}

	private void syncAssignationWithUser(User user, Assignation assignation) {
		user.removeAssignation(assignation);
	}

	private void syncAssignationWithCard(Card card, Assignation assignation) {
		card.removeAssignation(assignation);
	}

}
