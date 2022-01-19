package entity;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private Status status;
	private String message;
	private String description;

	public ErrorMessage() {
	}

	public ErrorMessage(Status status, String message, String description) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
