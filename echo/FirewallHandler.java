/*
 * @author Yunlin Xie
**/

package echo;

import java.util.*;

public class FirewallHandler extends ProxyHandler {
	private static Set<String> blocked = new HashSet<String>();
	private static void block(String cmmd) {
		synchronized(blocked) { blocked.add(cmmd); }
	}
	private static void unblock(String cmmd) {
		synchronized(blocked) { blocked.remove(cmmd); }
	}
	private static boolean isBlocked(String cmmd) {
		synchronized(blocked) { return blocked.contains(cmmd); }
	}
	protected String response(String request) {
		String result = "";
		String[] tokens = request.split("\\s+");
		if(tokens[0].equals("block")) {
			block(tokens[1]);
			result = tokens[1] + " is now blocked";
		} else if (tokens[0].equals("unblock")) {
			unblock(tokens[1]);
			result = tokens[1] + " is now un-blocked";
		} else if (isBlocked(tokens[0])) {
			result = "sorry, " + tokens[0] + " is blocked";
		} else {
			result = super.response(request);
		}
		return result;
	}
}