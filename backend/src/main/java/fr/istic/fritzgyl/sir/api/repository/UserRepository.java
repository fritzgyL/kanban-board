package fr.istic.fritzgyl.sir.api.repository;

import java.util.List;

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

}
