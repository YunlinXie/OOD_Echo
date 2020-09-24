// file provided by professor
// I implemented function reponse()

package echo;

import java.net.*;

public class ProxyHandler extends RequestHandler {

	protected Correspondent peer;

	public ProxyHandler(Socket s) { super(s); }
	public ProxyHandler() { super(); }

	public void initPeer(String host, int port) {
		peer = new Correspondent();
		peer.requestConnection(host, port);
	}

	protected String response(String msg) {
		peer.send(msg);
		msg = peer.receive();
		return msg;
	}
	// new feature:
	protected void shutDown() {
		super.shutDown();
		peer.send("quit");
	}
}
