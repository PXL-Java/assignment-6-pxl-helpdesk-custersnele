package be.pxl.helpdesk.builder;

import be.pxl.helpdesk.domain.Priority;
import be.pxl.helpdesk.domain.Status;
import be.pxl.helpdesk.domain.Ticket;
import be.pxl.helpdesk.domain.User;

import java.time.LocalDateTime;

public final class TicketBuilder {

	private long id;
	private Priority priority;
	private Status status;
	private LocalDateTime dateCreated;
	private User reporter;
	private String subject;
	private String body;

	private TicketBuilder() {}

	public static TicketBuilder aTicket() {return new TicketBuilder();}

	public TicketBuilder withId(long id) {
		this.id = id;
		return this;
	}

	public TicketBuilder withPriority(Priority priority) {
		this.priority = priority;
		return this;
	}

	public TicketBuilder withStatus(Status status) {
		this.status = status;
		return this;
	}

	public TicketBuilder withDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
		return this;
	}

	public TicketBuilder withReporter(User reporter) {
		this.reporter = reporter;
		return this;
	}

	public TicketBuilder withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public TicketBuilder withBody(String body) {
		this.body = body;
		return this;
	}

	public Ticket build() {
		Ticket ticket = new Ticket(reporter, subject, body);
		ticket.setId(id);
		ticket.setPriority(priority);
		ticket.setStatus(status);
		ticket.setDateCreated(dateCreated);
		return ticket;
	}
}
