package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "board")
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Section> sections = new ArrayList<Section>();

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

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", sections=" + sections + "]";
	}

}
