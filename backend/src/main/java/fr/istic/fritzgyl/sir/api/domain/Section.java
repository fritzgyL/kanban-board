package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity(name = "section")
@Table(name = "section")
@Cacheable(false)
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private long id;
	@Column(nullable = false)
	private String title;
	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	@XmlTransient
	@Schema(hidden = true)
	private Board board;
	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlTransient
	@Schema(hidden = true)
	private List<Card> cards = new ArrayList<Card>();
	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
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
		card.setSection(this);
		cards.add(card);
	}

	public void removeCard(Card card) {
		cards.remove(card);
		card.setSection(null);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String href, String rel) {
		links.add(new Link(href, rel));
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", title=" + title + ", board=" + board + ", cards=" + cards + "]";
	}

}