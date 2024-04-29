package be.pxl.helpdesk.builder;

import be.pxl.helpdesk.api.data.CreateTicketRequest;
import be.pxl.helpdesk.domain.Priority;

public final class CreateTicketRequestBuilder {

	private String subject;
	private String body;
	private String reporter;
	private Priority priority;

	private CreateTicketRequestBuilder() {}

	public static CreateTicketRequestBuilder aCreateTicketRequest() {return new CreateTicketRequestBuilder();}

	public CreateTicketRequestBuilder withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public CreateTicketRequestBuilder withBody(String body) {
		this.body = body;
		return this;
	}

	public CreateTicketRequestBuilder withReporter(String reporter) {
		this.reporter = reporter;
		return this;
	}

	public CreateTicketRequestBuilder withPriority(Priority priority) {
		this.priority = priority;
		return this;
	}

	public CreateTicketRequest build() {
		CreateTicketRequest createTicketRequest = new CreateTicketRequest();
		createTicketRequest.setSubject(subject);
		createTicketRequest.setBody(body);
		createTicketRequest.setReporter(reporter);
		createTicketRequest.setPriority(priority);
		return createTicketRequest;
	}
}
