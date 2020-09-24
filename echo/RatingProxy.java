/*
 * @author Yunlin Xie
**/

package echo;

import java.net.Socket;
import java.util.*;


public class RatingProxy extends ProxyHandler {
	private static Set<Double> ratings = new HashSet<Double>();
	private static Double avgRating = 0.0;
	
	public RatingProxy() {
		super();
	}
	public RatingProxy(Socket s) {
		super(s);
	}
	
	private double getAvgRating() {
		return avgRating;
	}
	
	public Double calculateAvg() {
		Double sum = 0.0;
		for (Double num: ratings) {
			sum += num;
		}
		
		avgRating = sum / ratings.size();
		avgRating = Math.round(avgRating * 10) / 10.0;
		return avgRating;
	}
	
	protected String response(String request) {
		String result = "";
		String[] elements = request.split("\\s+");
		String operatorType = elements[0].trim();
		
		if (operatorType.equalsIgnoreCase("rate")) {
			String rateString = elements[1].trim();
			Double currRate = Double.valueOf(rateString);
			ratings.add(currRate);
			calculateAvg();
			result = "average rating = " + String.valueOf(getAvgRating());
		} else {
			result = super.response(request);
		}
		
		return result;
	}
	

}
