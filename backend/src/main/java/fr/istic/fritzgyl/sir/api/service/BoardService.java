package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Board;
import fr.istic.fritzgyl.sir.api.exception.DataNotFoundException;
import fr.istic.fritzgyl.sir.api.repository.BoardRepository;
import fr.istic.fritzgyl.sir.api.repository.UserRepository;

public class BoardService {

	private BoardRepository boardRepository = new BoardRepository();
	private UserRepository userRepository = new UserRepository();

	public Iterable<Board> getAllBoards(long userId) {
		return boardRepository.getBoardsByUserId(userId);
	}

	public Board getBoard(long boardId) {
		Board board = boardRepository.read(boardId);
		if (board == null) {
			throw new DataNotFoundException("Board with id " + boardId + " not found");
		}
		return board;
	}

	public Board addBoard(long userId, Board board) {
		return userRepository.saveUserBoard(userId, board);
	}

	public Board updateBoard(Board board) {
		return boardRepository.update(board);
	}

	public void removeBoard(long boardId) {
		boardRepository.deleteAndRemoveFromUser(boardId);
	}

}
