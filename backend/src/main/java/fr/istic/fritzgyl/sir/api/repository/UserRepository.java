package fr.istic.fritzgyl.sir.api.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.istic.fritzgyl.sir.api.domain.Board;
import fr.istic.fritzgyl.sir.api.domain.User;

public class UserRepository extends GenericDaoJpaImpl<User, Long> {

	public List<User> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select u from user u", User.class).getResultList();
	}

	public Board saveUserBoard(long userId, Board board) {
//		entityManager.getTransaction().begin();
//		User user = read(userId);
//		user.addBoard(board);
//		entityManager.getTransaction().commit();
		return board;
	}

	public User login(String email) {
		User user = null;
		Query q = EntityManagerHelper.getEntityManager()
				.createQuery("select u from user u where u.email= :email", User.class).setParameter("email", email);
		try {
			user = (User) q.getSingleResult();
		} catch (NoResultException nre) {
		}
		return user;
	}

	public boolean checkIfIsEmailTaken(String email) {
		long count = ((Long) EntityManagerHelper.getEntityManager()
				.createQuery("select count(u) from user u where u.email= :email").setParameter("email", email)
				.getSingleResult()).intValue();
		return count > 0;
	}

}
