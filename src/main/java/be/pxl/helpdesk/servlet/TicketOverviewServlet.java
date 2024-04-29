package be.pxl.helpdesk.servlet;

import be.pxl.helpdesk.api.data.TicketCommentDTO;
import be.pxl.helpdesk.api.data.TicketDTO;
import be.pxl.helpdesk.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(value = "/mytickets")
public class TicketOverviewServlet extends HttpServlet {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

	private final TicketService ticketService;

	public TicketOverviewServlet(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writeHeader(writer);
		String reporter = req.getParameter("reporter");

		List<TicketDTO> allTickets = ticketService.findTickets(reporter);
		for (TicketDTO ticket : allTickets) {
			writeTicketDTO(writer, ticket);
		}
		writeFooter(writer);
	}
	private void writeTicketDTO(PrintWriter writer, TicketDTO ticket) {
		writer.println("<hr/>");
		writer.println("<div class=\"row\">");
		writer.println(ticket.getSubject() + " [" + ticket.getStatus() + "]");
		writer.println("</div>");
		writer.println("<div class=\"row\">");
		writer.println(ticket.getBody());
		writer.println("</div>");
		for (TicketCommentDTO comment : ticket.getComments()) {
			writer.println("<div class=\"row\">");
			writer.println(DATE_TIME_FORMATTER.format(comment.getDateTime()) + " - " + comment.getReporter().getUsername() + " : " + comment.getComment());
			writer.println("</div>");
		}
		writer.println("</div>");
	}

	private void writeHeader(PrintWriter writer) {
		writer.println("<html><head><title>Tickets</title></head><body>");
	}


	private void writeFooter(PrintWriter writer) {
		writer.println("</body></html>");
	}


}
