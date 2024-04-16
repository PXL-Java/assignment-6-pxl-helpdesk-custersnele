#!/bin/bash
PORT=8080
OUTPUT=100
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 1: OVERZICHT GEBRUIKERS\n'
curl -H Content-Type:application/json http://localhost:$PORT/users
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 2: TICKETS TOEVOEGEN\n'
curl -d '{"reporter": "589822@student.pxl.be", "subject": "problem 1","body":"long description of problem 1"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets
echo $''
curl -d '{"reporter": "589822@student.pxl.be", "subject": "problem 2","body":"long description of problem 2"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 3: TICKETS TOEVOEGEN ONGELDIG\n'
response=$(curl -s -d '{"reporter": "58982@student.pxl.be", "subject": "wrong ticket 1","body":"long description of wrong ticket"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "589822@student.pxl.be", "subject": "wrong ticket 2"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "589822@student.pxl.be", "subject": "wrong ticket 3", "priority":"UNCLEAR", "body":"long description of wrong ticket"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "417959@student.pxl.be", "subject": "wrong ticket 4", "body":"long description of wrong ticket"}' -H Content-Type:application/json -X POST http://localhost:$PORT/tickets)
echo ${response:0:$OUTPUT}
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 4: COMMENT TOEVOEGEN\n'
curl -d '{"reporter": "589822@student.pxl.be", "comment":"more description of problem 1"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/3
curl -d '{"reporter": "235340@pxl.be", "comment":"questions about problem 1"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/3
curl -d '{"reporter": "235340@pxl.be", "comment":"questions about problem 2"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/4
curl -d '{"reporter": "589822@student.pxl.be", "comment":"answers about problem 2"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/4
curl -d '{"reporter": "235340@pxl.be", "comment":"problem 2 solved", "solved": true}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/4
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 5: COMMENT TOEVOEGEN ONGELDIG\n'
response=$(curl -s -d '{"comment":"more description of problem 1"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/3)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "58982@student.pxl.be", "comment":"more description of problem 1"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/3)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "589822@student.pxl.be"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/3)
echo ${response:0:$OUTPUT}
response=$(curl -s -d '{"reporter": "589822@student.pxl.be", "comment":"more description of problem 1"}' -H Content-Type:application/json -X PUT http://localhost:$PORT/tickets/17)
echo ${response:0:$OUTPUT}
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 6: Servlet MyTickets\n'
curl -X GET http://localhost:$PORT/mytickets?reporter=jwhittlesey7

echo Press any key:
read key
