/*
 * @author Yunlin Xie
**/

package echo;

import java.util.HashMap;

public class Cache extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;
	
	public Cache() {
		super();
	}

	public synchronized String search(String request) {
		if (super.isEmpty()) {
			return null;
		}
		return super.get(request);
	}
	
	public synchronized void update(String request, String response) {
		super.put(request, response);
	}
	
}


/*
 * testing:
 * window 1:
 * java echo.Server math.MathHandler
 * window 2:
 * java echo.ProxyServer echo.CacheHandler 5555 6666
 * window 3:
 * java echo.SimpleClient 6666
 * -> add 2 3 4
 * 9
 * -> add 2 3 4 (no activity in MathHandler)
 * 9
 * */
 