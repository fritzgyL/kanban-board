package service;

import entity.Board;
import repository.BoardRepository;

public class BoardService {

	private BoardRepository boardRepository = new BoardRepository();

	public Iterable<Board> getBoards() {
		return boardRepository.findAll();
	}

	public Board getBoardById(long id) {
		return boardRepository.read(id);
	}

}
