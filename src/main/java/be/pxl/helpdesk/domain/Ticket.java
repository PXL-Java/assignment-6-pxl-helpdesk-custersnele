package be.pxl.helpdesk.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
    private User reporter;
    private String subject;
	private String body;

	@Enumerated(value = EnumType.STRING)
	private Priority priority = Priority.NORMAL;

	@Enumerated(value = EnumType.STRING)
	private Status status = Status.NEW;
    private LocalDateTime dateCreated;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketComment> comments = new ArrayList<>();

	public Ticket() {
		// JPA only
	}

	public Ticket(User reporter, String subject, String body) {
		this.reporter = reporter;
		this.subject = subject;
		this.body = body;
		dateCreated = LocalDateTime.now();
		status = Status.NEW;
	}

	public long getId()
    {
        return id;
    }

	public User getReporter() {
		return reporter;
	}

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }


	public Priority getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public LocalDateTime getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public int getNumberOfComments() {
	    // TODO: implement this method
		return comments.size();
    }

	public void solve() {
		status = Status.SOLVED;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void addComment(TicketComment comment) {
		comments.add(comment);
	}

	public List<TicketComment> getComments() {
		return comments;
	}


}
