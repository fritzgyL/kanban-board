package fr.istic.fritzgyl.sir.api.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "card")
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private Date deadline;
	@Column(name = "estimated_time")
	private int estimatedTime;
	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	private List<Tag> tags = new ArrayList<Tag>();
	private String location;
	private String url;
	private String description;
	@ManyToOne
	@XmlTransient
	private Section section;

	@Transient
	private List<Link> links = new ArrayList<>();

	public Card() {
	}

	public Card(String title, Section section) {
		this.title = title;
		this.section = section;
	}

	public Card(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Section getSection() {
		return section;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String url, String rel) {
		links.add(new Link(url, rel));
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", title=" + title + ", deadline=" + deadline + ", estimatedTime=" + estimatedTime
				+ ", tags=" + tags + ", location=" + location + ", url=" + url + ", description=" + description
				+ ", section=" + section + "]";
	}

}
