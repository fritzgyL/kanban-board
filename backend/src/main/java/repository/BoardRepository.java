package repository;

import java.util.List;

import entity.Board;

public class BoardRepository extends GenericDaoJpaImpl<Board, Long> {
	public List<Board> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select b from board b", Board.class).getResultList();
	}
}
