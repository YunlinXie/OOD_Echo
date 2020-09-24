/*
 * @author Yunlin Xie
**/

package echo;

import java.net.Socket;
import java.util.*;

public class SecurityProxy extends ProxyHandler {

	private static Map<String, String> users = new HashMap<String, String>();
	
	private String search(String request) {
		synchronized(users) {
			return users.get(request);
		}
	}
	private static void update(String request, String response) {
		synchronized(users) {
			users.put(request, response);
		}
	}
	private Boolean loggedIn;
	public SecurityProxy(Socket s) {
		super(s);
		loggedIn = false;
	}
	public SecurityProxy() {
		super();
		loggedIn = false;
	}
	protected String response(String request) {
		String result = "";
		if (loggedIn) {
			result = super.response(request);
		} else {
		   String[] tokens = request.split("\\s+");
		   if (tokens[0].equalsIgnoreCase("new")) {
			   if (search(tokens[1]) != null) {
				result = "User name taken";
			   } else {
			      update(tokens[1], tokens[2]);
			      result = "Account created";
			   }
		   } else if (tokens[0].equalsIgnoreCase("login")) {
			   if (search(tokens[1]).equals(tokens[2])) {
				  loggedIn = true;
				  result = "Login successful";
			   } else {
				   result = "Login failed";
			   }
		   } else {
			   result = "Please log in";
		   }
		}
		return result;
	}
}



/*
 *java echo.Server math.MathHandler
 *java echo.ProxyServer echo.CacheHandler 5555 6666
 *java echo.ProxyServer echo.SecurityHandler 6666 7777
 *java echo.SimpleClient 7777
 * */
 