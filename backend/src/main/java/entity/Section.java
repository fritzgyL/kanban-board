package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "section")
public class Section {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	private Board board;
	private List<Card> cards;
	private User creator;

	public Section() {
	}

	public Section(String title, User creator) {
		this.title = title;
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public long getId() {
		return id;
	}

	public Board getBoard() {
		return board;
	}

	public List<Card> getCards() {
		return cards;
	}

	public User getCreator() {
		return creator;
	}

}
