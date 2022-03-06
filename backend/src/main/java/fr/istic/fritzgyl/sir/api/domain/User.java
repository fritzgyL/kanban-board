package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

import javax.xml.bind.annotation.XmlAccessType;

@Entity(name = "user")
@Table(name = "user")
@Cacheable(false)
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private long id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(accessMode = AccessMode.WRITE_ONLY)
	@Column(nullable = false)
	private String email;
	@Schema(accessMode = AccessMode.WRITE_ONLY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@XmlTransient
	@Schema(hidden = true)
	private List<Board> boards = new ArrayList<Board>();
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@XmlTransient
	@Schema(hidden = true)
	private Set<Assignation> assignations = new HashSet<Assignation>();
	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void addLink(String href, String rel) {
		links.add(new Link(href, rel));
	}

//	public Set<Card> getAssignedCards() {
//		return assignedCards;
//	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}