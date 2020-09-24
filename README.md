# OOD_Echo

Refer to the link below for assignment introduction and requirements details.
http://www.cs.sjsu.edu/faculty/pearce/modules/projects/ood/simpleServer/echo/index.htm

Echo is a simple framework for building client-server applications.
The echo client is a simple console user interface that perpetually prompts its user for a request, forwards the request to a server, then displays the server's response.
Upon receiving a request, the server spawns a request handler thread connected to the client, then resumes listening for more requests.
The request handler's run method begins a request-response-loop with the client. The loop ends when the client sends the "quit" request.
Note: some code in this project are provided by the professor.