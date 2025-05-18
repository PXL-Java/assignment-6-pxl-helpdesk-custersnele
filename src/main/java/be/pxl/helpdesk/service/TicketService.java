package be.pxl.helpdesk.service;

import be.pxl.helpdesk.api.data.CreateTicketCommentRequest;
import be.pxl.helpdesk.api.data.CreateTicketRequest;
import be.pxl.helpdesk.api.data.TicketDTO;
import be.pxl.helpdesk.domain.*;
import be.pxl.helpdesk.exception.BusinessException;
import be.pxl.helpdesk.exception.NotFoundException;
import be.pxl.helpdesk.repository.TicketRepository;
import be.pxl.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;

	public TicketService(UserRepository userRepository, TicketRepository ticketRepository) {
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
	}

	public long createTicket(CreateTicketRequest createTicketRequest) {
		User reporter = userRepository.findUserByEmail(createTicketRequest.getReporter()).orElseThrow(() -> new NotFoundException("No user found with email [" + createTicketRequest.getReporter() + "]"));
		if (reporter.isLocked()) {
			throw new BusinessException("Account [" + createTicketRequest.getReporter() + "] is currently locked.");
		}
		Ticket ticket = new Ticket(reporter, createTicketRequest.getSubject(), createTicketRequest.getBody());
		ticket.setPriority(createTicketRequest.getPriority() == null? Priority.NORMAL : createTicketRequest.getPriority());
		ticket = ticketRepository.save(ticket);
		return ticket.getId();
	}

	@Transactional
	public void createTicketComment(long ticketId, CreateTicketCommentRequest createTicketCommentRequest) {
		User reporter = userRepository.findUserByEmail(createTicketCommentRequest.getReporter()).orElseThrow(() -> new NotFoundException("No user found with email [" + createTicketCommentRequest.getReporter() + "]"));
		if (reporter.isLocked()) {
			throw new BusinessException("Account [" + createTicketCommentRequest.getReporter() + "] is currently locked.");
		}
		// TODO check admin or original reporter
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new NotFoundException("No ticket with id [" + ticketId + "]"));
		if (createTicketCommentRequest.isSolved()) {
			// INTERESTING FOR TESTING: ticket.solve();
			ticket.setStatus(Status.SOLVED);
		} else if (ticket.getStatus() == Status.NEW && !ticket.getReporter().equals(reporter)) {
			ticket.setStatus(Status.OPEN);
		}
		new TicketComment(reporter, createTicketCommentRequest.getComment(), ticket);
	}

	@Transactional(readOnly = true)
	public List<TicketDTO> findTickets(String username) {
		return ticketRepository.findAllByReporter_Username(username).stream().map(TicketDTO::new).collect(Collectors.toList());
	}
}
