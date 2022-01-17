package entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	private Date deadline;
	private int estimatedTime;
	private List<Tag> tags;
	private String location;
	private String url;
	private String description;
	private Section section;
	private User creator;
	private User assignee;

	public Card() {
	}

	public Card(String title, Date deadline, User creator) {
		this.title = title;
		this.deadline = deadline;
		this.creator = creator;
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

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	

	public long getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public Section getSection() {
		return section;
	}

	public User getCreator() {
		return creator;
	}

}
