package repository;

import java.util.List;

import entity.Board;
import entity.Section;

public class BoardRepository extends GenericDaoJpaImpl<Board, Long> {
	public List<Board> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select b from board b", Board.class).getResultList();
	}

	public Board addSection(Long id, Section section) {
		entityManager.getTransaction().begin();
		Board board = read(id);
		board.addSection(section);
		entityManager.getTransaction().commit();
		return board;
	}
}
