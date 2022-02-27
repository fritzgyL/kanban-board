package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "tag")
@Table(name = "tag")
@Cacheable(false)
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String color;
	@ManyToOne
	@JoinColumn(name = "board_id")
	@XmlTransient
	private Board board;
	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	@XmlTransient
	private Set<Card> cards = new HashSet<Card>();
	@Transient
	private List<Link> links = new ArrayList<>();

	public Tag() {
	}

	public Tag(String title) {
		this.title = title;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String href, String rel) {
		links.add(new Link(href, rel));
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Set<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", title=" + title + ", color=" + color + ", board=" + board + ", cards=" + cards
				+ "]";
	}

}
