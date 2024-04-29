package be.pxl.helpdesk.api.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateTicketCommentRequest {

	@NotBlank
	private String comment;
	@Email
	@NotBlank
	private String reporter;
	private boolean solved;

	public String getComment() {
		return comment;
	}

	public String getReporter() {
		return reporter;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/*
	{
  "ticket": {
    "comment": {
      "body": "The smoke is very colorful."
    },
    "priority": "urgent",
    "subject": "My printer is on fire!"
  }
}
	 */

}