package be.pxl.helpdesk.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TicketComment")
public class TicketComment {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Ticket ticket;
	@ManyToOne
	private User reporter;
	private String comment;
	private LocalDateTime dateCreated;

	public TicketComment() {
		// JPA only
	}

	public TicketComment(User reporter, String comment, Ticket ticket) {
		this.comment = comment;
		this.ticket = ticket;
		this.reporter = reporter;
		ticket.addComment(this);
		dateCreated = LocalDateTime.now();
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public User getReporter() {
		return reporter;
	}

	public String getComment() {
		return comment;
	}
}