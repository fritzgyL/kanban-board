package fr.istic.fritzgyl.sir.api.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "card")
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	@Column(name = "estimated_time")
	private int estimatedTime;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "card_tag", joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	@XmlTransient
	private List<Tag> tags = new ArrayList<Tag>();
	private String location;
	private String url;
	private String description;
	@ManyToOne
	@JoinColumn(name = "section_id")
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
		tag.getCards().add(this);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getCards().remove(this);
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