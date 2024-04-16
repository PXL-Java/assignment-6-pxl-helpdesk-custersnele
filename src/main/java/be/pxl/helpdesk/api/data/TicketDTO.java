package be.pxl.helpdesk.api.data;

import be.pxl.helpdesk.domain.Priority;
import be.pxl.helpdesk.domain.Status;
import be.pxl.helpdesk.domain.Ticket;

public class TicketDTO {
	// TODO: this DTO is not complete!
	private final String subject;
	private final String body;
	private final Priority priority;
	private final Status status;

	public TicketDTO(Ticket ticket) {
		this.subject = ticket.getSubject();
		this.body = ticket.getBody();
		this.priority = ticket.getPriority();
		this.status = ticket.getStatus();
	}

	public String getBody() {
		return body;
	}

	public String getSubject() {
		return subject;
	}

	public Priority getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}
}
