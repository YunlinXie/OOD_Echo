// provided by the professor

package echo;

import java.util.*;

public class UserTable extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;
	
	public UserTable() {
		super();
	}

	public String getPassword(String username) {
		return get(username);
	}
	
	public synchronized void newUser(String username, String password) {
		put(username, password);
	}
	
}
