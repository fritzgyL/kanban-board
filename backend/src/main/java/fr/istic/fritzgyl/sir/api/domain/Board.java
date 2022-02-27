package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "board")
@Table(name = "board")
@Cacheable(false)

public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@XmlTransient
	private User user;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlTransient
	private List<Section> sections = new ArrayList<Section>();
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@XmlTransient
	private List<Tag> tags = new ArrayList<Tag>();
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

	public void addTag(Tag tag) {
		tags.add(tag);
		tag.setBoard(this);

	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.setBoard(null);
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

	public void addLink(String href, String rel) {
		links.add(new Link(href, rel));
	}

	public List<Tag> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", user=" + user + ", sections=" + sections + ", tags=" + tags
				+ "]";
	}

}