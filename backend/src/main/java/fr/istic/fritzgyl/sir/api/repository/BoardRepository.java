package fr.istic.fritzgyl.sir.api.repository;

import java.util.List;

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

	public Section saveBoardSection(long boardId, Section section) {
//		entityManager.getTransaction().begin();
//		System.out.println(section);
//		Board board = read(boardId);
//		board.addSection(section);
//		entityManager.getTransaction().commit();
		return section;
	}
}
