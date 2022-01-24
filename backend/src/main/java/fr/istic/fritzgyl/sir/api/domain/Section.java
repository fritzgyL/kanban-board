package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "section")
@Table(name = "section")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@ManyToOne
	@XmlTransient
	private Board board;
	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	private List<Card> cards = new ArrayList<Card>();
	@Transient
	private List<Link> links = new ArrayList<>();

	public Section() {
	}

	public Section(String title, Board board) {
		this.title = title;
		this.board = board;
	}

	public Section(String title) {
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		cards.add(card);
		card.setSection(this);
	}

	public void removeCard(Card card) {
		cards.remove(card);
		card.setSection(null);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", title=" + title + ", board=" + board + ", cards=" + cards + "]";
	}

}