package service;

import entity.Board;
import entity.Section;
import repository.BoardRepository;

public class BoardService implements Service<Board> {

	private BoardRepository boardRepository = new BoardRepository();

	@Override
	public Iterable<Board> getAll() {
		return boardRepository.findAll();

	}

	@Override
	public Board getById(long id) {
		return boardRepository.read(id);

	}

	@Override
	public Board save(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public void delete(long id) {
		boardRepository.delete(getById(id));
	}

	@Override
	public Board update(Board board) {
		return boardRepository.update(board);
	}

	public Board addSection(long id, Section section) {
		return boardRepository.addSection(id, section);
	}

}
