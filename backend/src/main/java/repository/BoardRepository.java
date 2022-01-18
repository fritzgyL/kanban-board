package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
