/*
 * @author Yunlin Xie
**/

package echo;

import java.net.Socket;
import java.util.*;

public class CacheProxy extends ProxyHandler {

	private static Map<String, String> cache = new HashMap<String, String>();

	private String search(String request) {
		synchronized(cache) {
			return cache.get(request);
		}
	}

	private void update(String request, String response) {
		synchronized(cache) {
			cache.put(request, response);
		}
	}

	public CacheProxy(Socket s) { super(s); }
	public CacheProxy() { super(); }

	protected String response(String request) {
		String result = search(request);
		if (result != null) {
			if (Server.DEBUG)
				System.out.println("found it!");
			return result;
		} else {
			result = super.response(request);
			update(request, result);
			return result;
		}
	}

}