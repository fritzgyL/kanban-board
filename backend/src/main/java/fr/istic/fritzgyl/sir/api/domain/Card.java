package fr.istic.fritzgyl.sir.api.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity(name = "card")
@Table(name = "card")
@Cacheable(false)
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private long id;
	@Column(nullable = false)
	private String title;
	@JsonFormat(pattern = "yyyy-M-dd", timezone = "Europe/Zagreb")
	private Date deadline;
	@OneToMany(mappedBy = "card", cascade = { CascadeType.ALL })
	@XmlTransient
	@Schema(hidden = true)
	private List<Tag> tags = new ArrayList<Tag>();
	@OneToMany(mappedBy = "card", cascade = { CascadeType.ALL })
	@XmlTransient
	@Schema(hidden = true)
	private Set<Assignation> assignations = new HashSet<Assignation>();
	private String location;
	private String url;
	private String description;
	@ManyToOne
	@JoinColumn(name = "section_id", nullable = false)
	@XmlTransient
	@Schema(hidden = true)
	private Section section;
	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<Link> links = new ArrayList<>();
	@Column(unique = true)
	private int position;

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
		tag.setCard(this);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.setCard(null);
	}

	public void addAssignation(Assignation assignation) {
		assignations.add(assignation);
		assignation.setCard(this);
	}

	public void removeAssignation(Assignation assignation) {
		assignations.remove(assignation);
		assignation.setCard(null);
	}

	public Set<Assignation> getAssignations() {
		return assignations;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void addLink(String href, String rel) {
		links.add(new Link(href, rel));
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", title=" + title + ", deadline=" + deadline + ", tags=" + tags + ", location="
				+ location + ", url=" + url + ", description=" + description + ", section=" + section + ", position="
				+ position + "]";
	}

}