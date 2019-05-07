/*************************************************
 * 												 *
 * This class is a POJO with getters and setters * 
 * for every read line of the input JSON.		 *
 * It is enabled with JSON annotations in case	 *
 * they are to be displayed in the webpage		 *
 *												 * 
 *************************************************/

package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
	"temp",
	"coord",
	"israning",
	"sunrise"
})

public class CityObject {
	
	/**
	 * Default constructor
	 */
	public CityObject() {
		
	}
	
	/**
	 * Constructor of a city object using the parameters
	 * @param name
	 * @param temp
	 * @param coord
	 * @param israining
	 * @param sunrise
	 */
	public CityObject(String name, int temp, Coord coord, boolean israining, int sunrise) {
		this.name = name;
		this.temp = temp;
		this.coord = coord;
		this.israining = israining;
		this.sunrise = sunrise;
	}
	@JsonProperty("name")
	private String name;
	@JsonProperty("temp")
	private Integer temp;
	@JsonProperty("coord")
	private Coord coord;
	@JsonProperty("israining")
	private Boolean israining;
	@JsonProperty("sunrise")
	private Integer sunrise;
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("temp")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("temp")
	public Integer getTemp() {
		return temp;
	}

	@JsonProperty("temp")
	public void setTemp(Integer temp) {
		this.temp = temp;
	}
	
	@JsonProperty("coord")
	public Coord getCoord() {
		return coord;
	}

	@JsonProperty("coord")
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	@JsonProperty("israining")
	public Boolean getIsRaining() {
		return israining;
	}

	@JsonProperty("israining")
	public void setIsRaining(Boolean israining) {
		this.israining = israining;
	}
	
	@JsonProperty("sunrise")
	public Integer getSunrise() {
		return sunrise;
	}

	@JsonProperty("sunrise")
	public void setSunrise(Integer sunrise) {
		this.sunrise = sunrise;
	}
	
	
	

}
