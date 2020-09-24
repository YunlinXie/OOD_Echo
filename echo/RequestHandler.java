// file provided by course professor
// my works is implementing function run() required parts

package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	protected boolean active = true;
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	
	protected String response(String msg) throws Exception {
	   return "echo: " + msg;
	}
	// new feature
	protected void shutDown() {
	   if (Server.DEBUG) System.out.println("handler shutting down");
	}
	public void run() {
		while(active) {
			try {
				String request = receive();
				if (request == null) continue;
				if (Server.DEBUG) System.out.println("received: " + request);
				if (request.equals("quit")) break;
				String response = response(request);
				if (Server.DEBUG) System.out.println("sending: " + response);
				send(response);
				Thread.yield(); // or sleep
			} catch(Exception e) {
				send(e.getMessage());
				if (Server.DEBUG) {
					send("... ending session");
					break;
				}
			}
		}
		shutDown(); // new feature
		close();
		if (Server.DEBUG) System.out.println("request handler shutting down");
	}

}



