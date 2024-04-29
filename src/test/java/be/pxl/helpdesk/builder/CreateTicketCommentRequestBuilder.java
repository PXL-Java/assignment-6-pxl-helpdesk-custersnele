package be.pxl.helpdesk.builder;

import be.pxl.helpdesk.api.data.CreateTicketCommentRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class CreateTicketCommentRequestBuilder {

	private String comment;
	private String reporter;
	private boolean solved;

	private CreateTicketCommentRequestBuilder() {}

	public static CreateTicketCommentRequestBuilder aCreateTicketCommentRequest() {return new CreateTicketCommentRequestBuilder();}

	public CreateTicketCommentRequestBuilder withComment(String comment) {
		this.comment = comment;
		return this;
	}

	public CreateTicketCommentRequestBuilder withReporter(String reporter) {
		this.reporter = reporter;
		return this;
	}

	public CreateTicketCommentRequestBuilder withSolved(boolean solved) {
		this.solved = solved;
		return this;
	}

	public CreateTicketCommentRequest build() {
		CreateTicketCommentRequest createTicketCommentRequest = new CreateTicketCommentRequest();
		createTicketCommentRequest.setComment(comment);
		createTicketCommentRequest.setReporter(reporter);
		createTicketCommentRequest.setSolved(solved);
		return createTicketCommentRequest;
	}
}
