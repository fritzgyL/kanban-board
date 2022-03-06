package fr.istic.fritzgyl.sir.api.domain;

public class ErrorMessage {
	private int status;
	private String message;
	private String description;

	public ErrorMessage() {
	}

	public ErrorMessage(int i, String message, String description) {
		super();
		this.status = i;
		this.message = message;
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
