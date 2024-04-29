package be.pxl.helpdesk.api.data;

import be.pxl.helpdesk.domain.Priority;
import be.pxl.helpdesk.domain.Status;
import be.pxl.helpdesk.domain.Ticket;

import java.util.List;

public class TicketDTO {
	private final String subject;
	private final String body;
	private final UserDTO reportedBy;
	private final int numberOfComments;
	private final List<TicketCommentDTO> comments;
	private final Priority priority;
	private final Status status;

	public TicketDTO(Ticket ticket) {
		this.subject = ticket.getSubject();
		this.reportedBy = new UserDTO(ticket.getReporter());
		this.body = ticket.getBody();
		this.numberOfComments = ticket.getNumberOfComments();
		this.comments = ticket.getComments().stream().map(TicketCommentDTO::new).toList();
		this.priority = ticket.getPriority();
		this.status = ticket.getStatus();
	}

	public String getBody() {
		return body;
	}

	public String getSubject() {
		return subject;
	}

	public UserDTO getReportedBy() {
		return reportedBy;
	}

	public int getNumberOfComments() {
		return numberOfComments;
	}

	public List<TicketCommentDTO> getComments() {
		return comments;
	}

	public Priority getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}
}
