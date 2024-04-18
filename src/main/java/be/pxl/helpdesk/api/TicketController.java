package be.pxl.helpdesk.api;

import be.pxl.helpdesk.api.data.CreateTicketCommentRequest;
import be.pxl.helpdesk.api.data.CreateTicketRequest;
import be.pxl.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping
	public long createTicket(@Valid @RequestBody CreateTicketRequest createTicketRequest) {
		return ticketService.createTicket(createTicketRequest);
	}

	@PutMapping(path = "{ticketId}")
	public void addComment(@PathVariable("ticketId") long ticketId, @Valid @RequestBody CreateTicketCommentRequest createTicketCommentRequest) {
		ticketService.createTicketComment(ticketId, createTicketCommentRequest);
	}
}