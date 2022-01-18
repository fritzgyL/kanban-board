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

	public Board saveBoard(Board board) {
		Board savedBoard = boardRepository.save(board);
		// boardRepository.refresh(savedBoard);
		return savedBoard;
	}

	public void deleteBoard(long id) {
		Board board = getBoardById(id);
		boardRepository.delete(board);
	}

}
