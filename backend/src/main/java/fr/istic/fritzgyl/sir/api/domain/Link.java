package fr.istic.fritzgyl.sir.api.domain;

public class Link {
	private String link;
	private String rel;

	public Link() {
		super();
	}

	public Link(String link, String rel) {
		this.link = link;
		this.rel = rel;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
