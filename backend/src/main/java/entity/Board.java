package entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Board {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	private List<Section> sections;
	private User creator;
	private Set<User> members;

	public Board() {

	}

	public Board(String title, User creator) {
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

	public List<Section> getSections() {
		return sections;
	}

	public User getCreator() {
		return creator;
	}

	public Set<User> getMembers() {
		return members;
	}

}
