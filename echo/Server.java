// file provided by the professor
// I implemented function listen() and makeHandler()
package echo;

import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		if (DEBUG) {
			System.out.println("Server address: " + mySocket.getInetAddress());
		}
		while(true) {
			try {
				if (DEBUG) {
					System.out.println("Server listening at port " + myPort);
				}
				// accept a connection
				Socket s = mySocket.accept();
				// make handler
				RequestHandler handler = makeHandler(s);
				if (handler == null) {
					System.out.println("no handler created!");
					continue;
				}
				// start handler in its own thread
				Thread t = new Thread(handler);
				t.start();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} // while
	}

	public RequestHandler makeHandler(Socket s) {
		// handler = a new instance of handlerType
		RequestHandler handler = null;
		try {
			handler = (RequestHandler)handlerType.getDeclaredConstructor().newInstance();
			// set handler's socket to s
			handler.setSocket(s);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		// return handler
		return handler;
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}