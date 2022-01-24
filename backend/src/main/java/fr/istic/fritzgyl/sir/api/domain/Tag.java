package fr.istic.fritzgyl.sir.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "tag")
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@ManyToOne
	@XmlTransient
	private Card card;
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

	public Card getCard() {
		return this.card;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", title=" + title + ", card=" + card + "]";
	}

}
