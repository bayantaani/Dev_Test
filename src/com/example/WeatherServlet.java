package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.OutputJsonObject;
import models.CityObject;
import models.Coord;
import models.OwmModel;


/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String APIKey = "d4e426deed9e49ba83e8507a80271557";
	private String baseURL = "http://api.openweathermap.org/data/2.5/";
	
	protected OutputJsonObject outJsonObj = new OutputJsonObject();
	
	//Here I read any exception description in case I want to display at at the error page
	//private Exception exception = null;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeatherServlet() {
		super();
	}

	/**
	 * TODO Complete the following method
	 * * You can use any Open Source library/code that will help you - 
	 *   * But the library then needs to be included (typically as jar file) in WebContent/WEB-INF/lib
	 *   * The code needs to work without us doing any extra work
	 * * You can take as long as you like 
	 *   * But we do not expect you to put in more than a few hours of work
	 *   * In practice you should be able to commit your solution within one week of cloning this repository
	 * * We expect readable coding 
	 *   * Methods and perhaps even classes are your friends!
	 *   * Comments are expected where it is not obvious what is happening
	 *   Clone the project into your own github repository. Once you are finished email us the name and branch of
	 *   your solution on github. We will clone and test it. Please refrain from changing your code once you have
	 *   contacted us.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Please read the following Data using the openweathermap API:
		// api.openweathermap.org/data/2.5/ - Documentation: https://openweathermap.org/api
		// You will need an API key - please request it from your interviewer

		// The weather for your hometown 

		// The weather for the town you were born
		// (If that happens to be the same town, then the town/location where you spend your last holiday)

		// Then compare the two 
		//   - difference in temperature
		//   - absolute difference in minutes between sunrises
		//   - distance (in km) <-- as a bonus
		// Also note where (if anywhere) it is currently raining.

		// Output the result as JSON Object with the attributes
		//     myHomeTown: '<name>'
		//     myHomeTownLocation: '<String with latitude longitude>'
		//     myOtherTown: '<name>'
		//     myOtherTownLocation: '<String with latitude longitude>'
		//     itIsRainingIn: '<Name of Town or "nowhere">'
		//	   sunriseTimeDifference: '<integer difference in minutes between sunrises>'
		//     tempDifference: <integer difference in temperature>
		//     distance: <integer distance in km (optional)>

		// if anything fails return a 500 error and a json object with the attributes 
		//     message: '<Possibly the message given by the failed api call>'
		//     error: true

		
		String myHomeTown = "Heidelberg, DE";
		String myOtherTown = "Singapore";

		String[] towns = {myHomeTown, myOtherTown};
		
		List <CityObject> cities = new ArrayList<CityObject>();
		
		String itsRainingIn = "";
		
		ObjectMapper objectMapper = new ObjectMapper();

		for (int i = 0; i<towns.length; i++) {
			
			String jsonOutput = recieveJSON(towns[i]);
			
			//if the function call failed for any reason, print an Error 500 message on the webpage along with the output object
			if (jsonOutput == null) {
				String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outJsonObj);				
				response.sendError(500, jsonStr);
				return;
			}

			//OwmModel is the POJO with getters and setters for all the attributes in the JSON schema
			OwmModel owm = new OwmModel();

			owm = objectMapper.readValue(jsonOutput, OwmModel.class);

			int temp = owm.getMain().getTemp() - 273;			
			Coord coord = owm.getCoord();
			boolean israining = (owm.getWeather().get(0).getMain().contains("Rain")? true: false);
			int sunrise = owm.getSys().getSunrise();

			cities.add(new CityObject(towns[i], temp, coord, israining, sunrise));
			if (israining == true) {
				if (itsRainingIn == "") {
				itsRainingIn = towns[i];
				} else {
				itsRainingIn += ", " + towns[i];
				}
			}
		}
		
		int tempDiff = tempDifference(cities.get(0).getTemp(), cities.get(1).getTemp());
		int sunriseDiff = sunriseDifference(cities.get(0).getSunrise(), cities.get(1).getSunrise());
		int dist = distance(cities.get(0).getCoord(), cities.get(1).getCoord());
		if (itsRainingIn == "") itsRainingIn = "Nowhere";
		
		outJsonObj = new OutputJsonObject(cities.get(0).getName(), cities.get(1).getName(),
				tempDiff, cities.get(0).getCoord(), cities.get(1).getCoord(),
				itsRainingIn, sunriseDiff, dist);
		
		//Print the resulting JSON object on the webpage
		String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outJsonObj);
		response.getWriter().append("\n" + jsonStr);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Connect to the URL and return the received JSON string. If the function fails for any reason, return null
	 * @param town: town name
	 * @return JSON response as String
	 */
	private String recieveJSON(String town) {

		String output = "";
		try {
			URL url = new URL(baseURL+"weather?q="+town+"&appid="+APIKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = "";

			while ((line = br.readLine()) != null) {
				output += line;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			//exception = e;
			return null;

		} catch (IOException e) {
			//exception = e;
			return null;
		}
		return output;
	}


	/**
	 * Calculate the difference in sunrise time between the two cities
	 * @param city1: Sunrise time of city1
	 * @param city2: Sunrise time of city2
	 * @return Difference in minutes
	 */
	private int sunriseDifference(int city1, int city2) {
		return Math.abs(city1 - city2)/60;
	}

	/**
	 * Calculate the difference in temperature between the two cities
	 * @param city1
	 * @param city2
	 * @return Absolute value of temperature difference
	 */
	private int tempDifference(int city1, int city2) {
		return Math.abs(city1 - city2);
	}

	/**
	 * Calculate the distance between the two cities
	 * The implementation of this function is from GeoDataSource: https://www.geodatasource.com/developers/java
	 * @param city1: Coordinates of city1
	 * @param city2: Coordinates of city2
	 * @return The distance in km
	 */
	private int distance(Coord city1, Coord city2) {

		double lat1 = city1.getLat();
		double lon1 = city1.getLon();
		double lat2 = city2.getLat();
		double lon2 = city2.getLon();
		
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return ((int) dist);
		}
	}

}
