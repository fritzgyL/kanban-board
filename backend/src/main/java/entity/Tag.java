package entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	private Set<Card> cards;
	private User creator;

	public Tag() {
	}

	public Tag(String title, User creator) {
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

	public Set<Card> getCards() {
		return cards;
	}

	public User getCreator() {
		return creator;
	}

}
