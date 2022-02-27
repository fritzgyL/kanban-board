package fr.istic.fritzgyl.sir.api.domain;

public class Link {
	private String href;
	private String rel;

	public Link() {
		super();
	}

	public Link(String link, String rel) {
		this.href = link;
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
