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

@Entity(name = "board")
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@ManyToOne
	@XmlTransient
	private User user;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	private List<Section> sections = new ArrayList<Section>();
	@Transient
	private List<Link> links = new ArrayList<>();

	public Board() {

	}

	public Board(String title) {
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

	public List<Section> getSections() {
		return sections;
	}

	public void addSection(Section section) {
		sections.add(section);
		section.setBoard(this);
	}

	public void removeSection(Section section) {
		sections.remove(section);
		section.setBoard(null);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", user=" + user + ", sections=" + sections + "]";
	}

}
