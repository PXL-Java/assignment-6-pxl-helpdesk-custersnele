package be.pxl.helpdesk.api.data;

import be.pxl.helpdesk.domain.Priority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateTicketRequest {

	@NotBlank
	private String subject;
	@NotBlank
	private String body;
	@Email
	private String reporter;
	private Priority priority;

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getReporter() {
		return reporter;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
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