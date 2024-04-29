package be.pxl.helpdesk.api;

import be.pxl.helpdesk.api.data.CreateTicketCommentRequest;
import be.pxl.helpdesk.builder.CreateTicketCommentRequestBuilder;
import be.pxl.helpdesk.exception.BusinessException;
import be.pxl.helpdesk.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TicketController.class)
public class TicketControllerTest {

		@Autowired
		private MockMvc mockMvc;

		@MockBean
		private TicketService ticketService;

		@Autowired
		private ObjectMapper objectMapper;

		@Test
		public void createTicketCommentAndServiceThrowBusinessException() throws Exception {
			CreateTicketCommentRequest request = CreateTicketCommentRequestBuilder.aCreateTicketCommentRequest()
					.withReporter("rep@pxl.be").withComment("Test").withSolved(false).build();
			String json = objectMapper.writeValueAsString(request);
			doThrow(BusinessException.class).when(ticketService)
					 .createTicketComment(Mockito.eq(5L), Mockito.any(CreateTicketCommentRequest.class));
			mockMvc.perform(put("/tickets/5")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json))
					.andExpect(status().isBadRequest());

		}


	}
