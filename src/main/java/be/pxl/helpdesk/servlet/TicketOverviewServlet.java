package be.pxl.helpdesk.servlet;

import be.pxl.helpdesk.api.data.TicketDTO;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class TicketOverviewServlet {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

	private void writeTicketDTO(PrintWriter writer, TicketDTO ticket) {
		writer.println("<hr/>");
		writer.println("<div class=\"row\">");
		writer.println(ticket.getSubject() + " [" + ticket.getStatus() + "]");
		writer.println("</div>");
		writer.println("<div class=\"row\">");
		writer.println(ticket.getBody());
		writer.println("</div>");
		// TODO: display the comments
		writer.println("</div>");
	}

	private void writeHeader(PrintWriter writer) {
		writer.println("<html><head><title>Tickets</title></head><body>");
	}


	private void writeFooter(PrintWriter writer) {
		writer.println("</body></html>");
	}


}
