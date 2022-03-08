package fr.istic.fritzgyl.sir.api.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity(name = "assignation")
@Table(name = "assignation")
@Cacheable(false)
public class Assignation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "card_id", nullable = false)
	private Card card;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Assignation(User user, Card card) {
		this.user = user;
		this.card = card;
	}

	public Assignation() {
		// TODO Auto-generated constructor stub
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

}
