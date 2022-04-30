package fr.istic.fritzgyl.sir.api.dto;

public class AssignationGetDTO {

	private String assigneeFirstName;
	private String assigneeLastName;
	private int id;

	public String getAssigneeFirstName() {
		return assigneeFirstName;
	}

	public void setAssigneeFirstName(String assigneeFirstName) {
		this.assigneeFirstName = assigneeFirstName;
	}

	public String getAssigneeLastName() {
		return assigneeLastName;
	}

	public void setAssigneeLastName(String assigneeLastName) {
		this.assigneeLastName = assigneeLastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
