package fr.istic.fritzgyl.sir.api.repository;

import java.util.List;

import javax.persistence.EntityManager;

import fr.istic.fritzgyl.sir.api.domain.Board;
import fr.istic.fritzgyl.sir.api.domain.Section;

public class BoardRepository extends GenericDaoJpaImpl<Board, Long> {
	public List<Board> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select b from board b", Board.class).getResultList();
	}

	public Iterable<Board> getBoardsByUserId(long userId) {
		return EntityManagerHelper.getEntityManager()
				.createQuery("select b from board b where b.user.id=:userId", Board.class)
				.setParameter("userId", userId).getResultList();
	}

	public void deleteAndRemoveFromUser(long id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Board board = em.find(Board.class, id);
		board.getUser().removeBoard(board);
		em.remove(em.contains(board) ? board : em.merge(board));
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
	}
	
	public Section saveBoardSection(long boardId, Section section) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Board board = read(boardId);
		board.addSection(section);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return section;
	}
}
