package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "user")
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	private List<Board> boards = new ArrayList<Board>();
	@Transient
	private List<Link> links = new ArrayList<>();

	public User() {
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void addBoard(Board board) {
		boards.add(board);
		board.setUser(this);
	}

	public void removeBoard(Board board) {
		boards.remove(board);
		board.setUser(null);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", boards=" + boards + "]";
	}

}