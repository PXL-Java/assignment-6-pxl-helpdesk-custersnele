package be.pxl.helpdesk.service;

import be.pxl.helpdesk.api.data.CreateTicketRequest;
import be.pxl.helpdesk.builder.CreateTicketRequestBuilder;
import be.pxl.helpdesk.builder.TicketBuilder;
import be.pxl.helpdesk.builder.UserBuilder;
import be.pxl.helpdesk.domain.Priority;
import be.pxl.helpdesk.domain.Ticket;
import be.pxl.helpdesk.domain.User;
import be.pxl.helpdesk.exception.BusinessException;
import be.pxl.helpdesk.exception.NotFoundException;
import be.pxl.helpdesk.repository.TicketRepository;
import be.pxl.helpdesk.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private TicketRepository ticketRepository;
	@InjectMocks
	private TicketService ticketService;
	@Captor
	private ArgumentCaptor<Ticket> ticketArgumentCaptor;

	@Test
	public void createTicket_reporterEmailDoesNotExist_ThrowsNotFoundException() {
		CreateTicketRequest createTicketRequest = CreateTicketRequestBuilder
				.aCreateTicketRequest()
				.withReporter("rep@pxl.be")
				.build();
		Mockito.when(userRepository.findUserByEmail2("rep@pxl.be")).thenReturn(Optional.empty());

		Assertions.assertThrows(NotFoundException.class,
				() -> ticketService.createTicket(createTicketRequest));
	}

	@Test
	public void createTicket_reporterIsLocked_ThrowsBusinessException() {
		CreateTicketRequest createTicketRequest = CreateTicketRequestBuilder
				.aCreateTicketRequest()
				.withReporter("rep@pxl.be")
				.build();
		User reporter = UserBuilder.anUser().withEmail("rep@pxl.be").withLocked(true).build();
		Mockito.when(userRepository.findUserByEmail2("rep@pxl.be")).thenReturn(Optional.of(reporter));

		Assertions.assertThrows(BusinessException.class,
				() -> ticketService.createTicket(createTicketRequest));
	}

	@Test
	public void createTicket_ticketWithStatusNewIsSaved() {
		CreateTicketRequest createTicketRequest = CreateTicketRequestBuilder
				.aCreateTicketRequest()
				.withPriority(Priority.HIGH)
				.withReporter("rep@pxl.be")
				.withSubject("Problem")
				.withBody("Really big problem...")
				.build();
		User reporter = UserBuilder.anUser().withEmail("rep@pxl.be").withLocked(false).build();
		Mockito.when(userRepository.findUserByEmail2("rep@pxl.be")).thenReturn(Optional.of(reporter));
		Ticket mockedTicket = Mockito.mock(Ticket.class);
		Mockito.when(mockedTicket.getId()).thenReturn(5L);
		Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(mockedTicket);

		long ticketId = ticketService.createTicket(createTicketRequest);

		Assertions.assertEquals(5L, ticketId);
		Mockito.verify(ticketRepository).save(ticketArgumentCaptor.capture());
		Ticket newTicket = ticketArgumentCaptor.getValue();
		Assertions.assertEquals(reporter, newTicket.getReporter());
		Assertions.assertEquals("Problem", newTicket.getSubject());
		Assertions.assertEquals("Really big problem...", newTicket.getBody());
		Assertions.assertEquals(Priority.HIGH, newTicket.getPriority());
	}
}
