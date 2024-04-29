package be.pxl.helpdesk.api.data;

import be.pxl.helpdesk.domain.TicketComment;

import java.time.LocalDateTime;

public class TicketCommentDTO {
	private final UserDTO reporter;
	private final String comment;
	private final LocalDateTime dateTime;

	public TicketCommentDTO(TicketComment ticketComment) {
		this.reporter = new UserDTO(ticketComment.getReporter());
		this.comment = ticketComment.getComment();
		this.dateTime = ticketComment.getDateCreated();
	}

	public UserDTO getReporter() {
		return reporter;
	}

	public String getComment() {
		return comment;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
}