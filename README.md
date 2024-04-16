# PXL Helpdesk API

## 1. H2 database

http://localhost:8080/h2-console

database url and credentials can be found in `appplication.properties`.

## 2. API documentation

### Get list of users

#### Request

`GET /users/`

    e.g. http://localhost:8080/users


#### Response

    200 OK
    Content-Type: application/json

    [{"username":"spountney0",
      "email":"820179@student.pxl.be",
      "role":"USER","locked":false}
     ,
     ...]

### Create a ticket

#### Request

`POST /tickets`

    {
        "reporter": "589822@student.pxl.be",
        "subject": "Cannot eject dvd from my laptop.",
        "body": "Dvd is stuck in my laptop.",
        "priority": "HIGH"
    }


#### Response

    201 Created
    Unique id of the ticket

    404 Not Found
    e.g. in case the email of the reporter cannot be found

    400 Bad Request
    e.g. in case the subject of body is missing or the priority is wrong
    

### Create a ticket comment

#### Request
`PUT /tickets/{ticket_id}`
where parameter `{ticket_id}` refers to a valid id of a ticket

    {
        "reporter": "235340@pxl.be",
        "comment": "Is there actually a dvd-player in your laptop?",
        "solved": false
    }

#### Responses

    200 OK (no response body)
    
    400 Bad Request
    Missing fields (e.g. reporter or comment)

    404 Not Found
    Invalid reporter or invalid ticket id

### Validation script 

In the resources directory you can find a script validate_endpoints.sh to validate
your solution.

When you run the script you should see the following output:

    +++++++++++++++++++++++++++++++++++++++++++++++++++
    
    CONTROLE 1: OVERZICHT GEBRUIKERS
    [{"username":"spountney0","email":"820179@student.pxl.be","role":"USER","locked":false},
     {"username":"edudleston1","email":"558008@student.pxl.be","role":"USER","locked":false},
     {"username":"iimesson2","email":"875878@pxl.be","role":"USER","locked":false},
     {"username":"vcristofaro3","email":"718725@student.pxl.be","role":"USER","locked":false},
     {"username":"dspira4","email":"378874@student.pxl.be","role":"USER","locked":false},
     {"username":"pawson5","email":"235340@pxl.be","role":"ADMIN","locked":false},
     {"username":"cmaccoughan6","email":"985847@student.pxl.be","role":"USER","locked":false},
     {"username":"jwhittlesey7","email":"589822@student.pxl.be","role":"USER","locked":false},
     {"username":"bpitt8","email":"753042@student.pxl.be","role":"USER","locked":false},
     {"username":"gguppey9","email":"571121@student.pxl.be","role":"USER","locked":false},
     {"username":"emcgrilla","email":"293121@student.pxl.be","role":"USER","locked":false},
     {"username":"odreinib","email":"506341@student.pxl.be","role":"USER","locked":false},
     {"username":"kmillmoec","email":"775466@student.pxl.be","role":"USER","locked":false},
     {"username":"gkropd","email":"861568@pxl.be","role":"ADMIN","locked":false},
     {"username":"pmaccroarye","email":"370163@student.pxl.be","role":"USER","locked":false},
     {"username":"scavetf","email":"729725@student.pxl.be","role":"USER","locked":false},
     {"username":"lgiacobonig","email":"248976@student.pxl.be","role":"USER","locked":false},
     {"username":"acoxonh","email":"887902@student.pxl.be","role":"USER","locked":false},
     {"username":"mcraggi","email":"928156@pxl.be","role":"USER","locked":false},
     {"username":"pbatripj","email":"567708@student.pxl.be","role":"USER","locked":false},
     {"username":"mdonsonk","email":"924681@student.pxl.be","role":"USER","locked":false},
     {"username":"veasuml","email":"400632@pxl.be","role":"ADMIN","locked":false},
     {"username":"tbancroftm","email":"417959@student.pxl.be","role":"USER","locked":true},
     {"username":"zjakubskin","email":"636095@student.pxl.be","role":"USER","locked":false},
     {"username":"wloramo","email":"714839","role":"USER","locked":false}]
    +++++++++++++++++++++++++++++++++++++++++++++++++++
    CONTROLE 2: TICKETS TOEVOEGEN
    3
    4
    +++++++++++++++++++++++++++++++++++++++++++++++++++
    CONTROLE 3: TICKETS TOEVOEGEN ONGELDIG
    {"timestamp":"2022-09-05T10:35:52.896+00:00","status":404,"error":"Not Found","trace":"be.pxl.helpde
    {"timestamp":"2022-09-05T10:35:52.938+00:00","status":400,"error":"Bad Request","trace":"org.springf
    {"timestamp":"2022-09-05T10:35:52.964+00:00","status":400,"error":"Bad Request","trace":"org.springf
    {"timestamp":"2022-09-05T10:45:19.009+00:00","status":400,"error":"Bad Request","trace":"be.pxl.help
    +++++++++++++++++++++++++++++++++++++++++++++++++++
    CONTROLE 4: COMMENT TOEVOEGEN
    +++++++++++++++++++++++++++++++++++++++++++++++++++
    CONTROLE 5: COMMENT TOEVOEGEN ONGELDIG
    {"timestamp":"2022-09-05T10:35:53.091+00:00","status":400,"error":"Bad Request","trace":"org.springf
    {"timestamp":"2022-09-05T10:35:53.110+00:00","status":404,"error":"Not Found","trace":"be.pxl.helpde
    {"timestamp":"2022-09-05T10:35:53.128+00:00","status":400,"error":"Bad Request","trace":"org.springf
    {"timestamp":"2022-09-05T10:35:53.147+00:00","status":404,"error":"Not Found","trace":"be.pxl.helpde
    +++++++++++++++++++++++++++++++++++++++++++++++++++
    CONTROLE 6: Servlet MyTickets
    
    <html><head><title>Tickets</title></head><body>
    <hr/>
    <div class="row">
    problem 1 [OPEN]
    </div>
    <div class="row">
    long description of problem 1
    </div>
    <div class="row">
    05-09-2022 12:35:53 - pawson5 : questions about problem 1
    </div>
    <div class="row">
    05-09-2022 12:35:52 - jwhittlesey7 : more description of problem 1
    </div>
    </div>
    <hr/>
    <div class="row">
    problem 2[SOLVED]
    </div>
    <div class="row">
    long description of problem 2
    </div>
    <div class="row">
    05-09-2022 12:35:53 - pawson5 : questions about problem 2
    </div>
    <div class="row">
    05-09-2022 12:35:53 - jwhittlesey7 : answers about problem 2
    </div>
    <div class="row">
    05-09-2022 12:35:53 - pawson5 : problem 2 solved
    </div>
    </div>
    </body></html>
    Press any key:
