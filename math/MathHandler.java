/*
 * @author Yunlin Xie
 **/

package math;

import java.net.Socket;
import java.util.*;

import echo.RequestHandler;

public class MathHandler extends RequestHandler {
	// addition
	public String add(List<Double> list) {
		double res = 0;
		for (double i: list) {
			res += i;
		}
		res = Math.round(res * 10) / 10.0;
		return String.valueOf(res);
	}
	//subtraction
	public String sub(List<Double> list) {
		double res = list.get(0);
		for (int i=1; i<list.size(); i++) {
			res -= list.get(i);
		}
		res = Math.round(res * 10) / 10.0;
		return String.valueOf(res);
	}
	// multiplication
	public String mul(List<Double> list) {
		double res = 1;
		for (double i: list) {
			res *= i;
		}
		res = Math.round(res * 10) / 10.0;
		return String.valueOf(res);
	}
	// division
	public String div(List<Double> list) {
		double res = list.get(0);
		for (int i=1; i<list.size(); i++) {
			if (list.get(i) == 0) {
				return "divisor cannot be zero";
			} else {
				res /= list.get(i);
			}
		}
		res = Math.round(res * 10) / 10.0;
		return String.valueOf(res);
	}	
	
	public MathHandler() {
		super();
	}
	
	public MathHandler(Socket sock) {
		super(sock);
	}

	// get the operator: the first word of inputing string
	public String getOperator(String request) {
		String operator = "";
		String[] elements = request.split("\\s+");
		operator = elements[0].trim();
		return operator;
	}
	
	// get the numbers for operating and check their validation
	public List<Double> getNums(String request) {
		List<Double> nums = new ArrayList<Double>();
		String[] elements = request.split("\\s+");
		if (elements == null) {
			return null;
		}
		
		for (int i=1; i<elements.length; i++) {
			String s = elements[i].trim();
			try {
				double num = Double.parseDouble(s);
				nums.add(num);
			} catch (NumberFormatException e) {
				return null; 
			}
		}
		return nums;
	}
	
	protected String response(String request) throws Exception {
		String operatorType = getOperator(request);
		List<Double> nums = getNums(request);
		String result = "";
		if (nums == null) {
			return "Invalid input";
		}
		
		if (operatorType.equalsIgnoreCase("add")) {
			result = add(nums);		
		} else if (operatorType.equalsIgnoreCase("sub")) {
			result = sub(nums);
		} else if (operatorType.equalsIgnoreCase("mul")) {
			result = mul(nums);
		} else if (operatorType.equalsIgnoreCase("div")) {
			result = div(nums);
		} else {
			result = "Unrecognized operator: " + operatorType;
		}
		return result;
	}
	
	

}
