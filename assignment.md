## De Helpdesk toepassing

PXL wants to create a helpdesk application where students and teachers can report problems.
For the PXL Helpdesk you will develop a RESTful API in Spring Boot.
You will implement some user stories.
You can find additional information in [README.md](README.md).
Respect the 3-tier architecture of the application!


### Given
We use an H2 database for our implementation.
The tables are automatically created when the application is started. During startup, the code in the ImportData class (package be.pxl.helpdesk.config) is also executed, which loads data into the database.

Concepts in the application:
- **User**: User of the system. Users have a self-chosen username and a pxl email address. User management is outside the scope of the assignment. Each user has a role: ADMIN or USER. A user's account can also be locked.
- **Ticket**: A ticket is a report by a user to the helpdesk. A ticket has a subject, a body (content of the report), a priority (enum Priority: HIGH, NORMAL, LOW), a status (enum Status: NEW, OPEN, SOLVED), and the user who created the report (reporter).

These classes can be found in package be.pxl.helpdesk.domain.

### Task 1

Annotate the entity classes User and Ticket. (NO bidirectional relationship - see ER-diagram)


<img src="images/user_ticket.png" alt="user_ticket" style="width:300px;"/>**

Add the necessary annotations in the class UserController.
The endpoint  ‘GET /helpdesk/users’ should be available now (see README.md). 
Test the endpoint.


### Task 2: User story 1

**As a user I can create a new ticket.**

**Description:**

As a user I can provide my e-mail address (reporter), a subject 
and a body to create a new ticket for the helpdesk.
The priority of the ticket can be given by the reporter.

```
POST http://localhost:8080/tickets (zie ook README.md)

{
"reporter": "589822@student.pxl.be",
"subject": "Cannot eject dvd from my laptop.",
"body": "Dvd is stuck in my laptop.",
"priority": "HIGH"
}
```

The response is a unique identification of the newly created
ticket or an error message.

**Acceptance criteria:**\
☐ The e-mail address of the user (reporter) is mandatory.\
☐ The subject and the body are mandatory.\
☐ The e-mail address must be valid (format).\
☐ priority is NOT mandatory. The default priority is NORMAL.\
☐ If there is no user with the given e-mail address, a NotFoundException is thrown. This exception results in http status NOT_FOUND. The ticket is not saved when an error occurs.\
☐ If the user's account is locked, a BusinessException is thrown. This exception results in a http status BAD_REQUEST.\
☐ The newly created ticket has status NEW.\

**Implementation details:**\
Create a new RestController: TicketController. Here you create the REST endpoint.
Create a new class TicketService where you create the method createTicket(…) with all the business-logic.

**Unit tests (JUnit / Mockito):**\
☐  Create 2 relevant unit tests for the method createTicket(…) in class TicketService.


### Task 3: User story 2

**As an admin or reporter of a ticket, I can add comments to a ticket.**

**Description:**

Only the reporter of a ticket or an administrator (role ADMIN) can add a comment to a ticket.
The user adding a comment to a ticket provides their email address and the comment. 
Once a first comment is added to a ticket, the status of the ticket changes from NEW to OPEN. 
The boolean field 'solved' is optional. If the value is true, the status of the ticket is changed to SOLVED.
```
PUT http://localhost:8080/tickets/4 (zie ook README.md)

{
"reporter": "235340@pxl.be",
"comment": "Is there actually a dvd-player in your laptop?",
"solved": false
}
```

**Acceptance criteria:**\
☐ The user's (reporter's) email address is mandatory.\
☐ The provided email address must be valid.\
☐ If no user exists with the provided email address, then a NotFoundException is thrown. This exception returns an HTTP status of NOT_FOUND. In this case, the comment is not saved.\
☐ If the user's account is locked, then a BusinessException is thrown. This exception returns an HTTP status of BAD_REQUEST. In this case, the comment is not saved.\
☐ Only the reporter of the ticket or a user with the role ADMIN is allowed to create a comment for a ticket. If this requirement is not met, a BusinessException is thrown. This exception returns an HTTP status of BAD_REQUEST. In this case, the comment is not saved.\
☐ The provided ticketId (in the URL) must be a valid ticketId. If no ticket exists with the provided ticketId, then a NotFoundException is thrown. This exception returns an HTTP status of NOT_FOUND. In this case, the comment is not saved.\
☐ If all data is valid, the comment is saved in the database.\
☐ The status of the ticket is changed from NEW to OPEN when a first valid comment is added.\
☐ The status of the ticket is changed from NEW or OPEN to SOLVED when the boolean field 'solved' is true.\

**Unit tests (JUnit / Mockito / MockMvc):**\
☐ Write 1 unit test for the RestController for the successful creation of a commment.\
☐ Write 1 unit test for the RestController where the user's account is locked (and thus a BusinessException is thrown by the service-layer).\

**Implementation details:**\
☐ Create the REST endpoint.\
☐ Add the validation.\
☐ Create an entity class TicketComment with a bidirectional relationship with Ticket.
(see class diagram).\
☐ Immplement the business logic, exception-handling, and unit tests.\

### Task 4: User story 3

**As a user, I can get an overview of the tickets I have created.**

**Description:**

Create a WebServlet OR a Thymeleaf template that generates an overview of all tickets of a user.
"reporter" is a parameter that you should provide.

```
URL: http://localhost:8080/mytickets?reporter=jwhittlesey7
```

**Acceptance criteria:**\
☐ A clear HTML page displaying all tickets created by the reporter with the provided username is shown. All associated comments (in chronological order) are displayed alongside the tickets.

**Implementation details:**\
You can choose whether to use a servlet or Thymeleaf. If you choose a Servlet, you can use the helper methods in the class TicketOverviewServlet.

You will also need to implement a query to retrieve all tickets created by the user with the provided username.

![servlet.png](images%2Fservlet.png)


**Class diagram:**
[diagram.pdf](images%2Fdiagram.pdf)






