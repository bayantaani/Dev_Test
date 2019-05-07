/*************************************************
 * 												 *
 * This class is a POJO with getters and setters * 
 * for the output JSON object.					 *
 * 												 *
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

public class OutputJsonObject {
	
	/**
	 * Default constructor
	 */
	public OutputJsonObject() {
		this.name1 = "";
		this.name2 = "";
		this.tempDiff = 0;
		this.coord1 = new Coord();
		this.coord2 = new Coord();
		this.itsRainingIn = "";
		this.sunriseDiff = 0;
		this.dist = 0;
	}
	
	/**
	 * Constructor using parameters
	 * @param name1
	 * @param name2
	 * @param tempDiff
	 * @param coord1
	 * @param coord2
	 * @param itsRainingIn
	 * @param sunriseDiff
	 * @param dist
	 */
	public OutputJsonObject(String name1, String name2, int tempDiff, Coord coord1, Coord coord2, String itsRainingIn, int sunriseDiff, int dist) {
		this.name1 = name1;
		this.name2 = name2;
		this.tempDiff = tempDiff;
		this.coord1 = coord1;
		this.coord2 = coord2;
		this.itsRainingIn = itsRainingIn;
		this.sunriseDiff = sunriseDiff;
		this.dist = dist;
	}
	@JsonProperty("MyHomeTown")
	private String name1;
	@JsonProperty("MyHomeTownLocation")
	private Coord coord1;	
	@JsonProperty("MyOtherTown")
	private String name2;
	@JsonProperty("MyOtherTownLocation")
	private Coord coord2;
	@JsonProperty("itsRainingIn")
	private String itsRainingIn;
	@JsonProperty("sunriseTimeDifference")
	private Integer sunriseDiff;
	@JsonProperty("tempDifference")
	private Integer tempDiff;
	@JsonProperty("distance")
	private Integer dist;
	
	@JsonProperty("MyHomeTown")
	public String getName1() {
		return name1;
	}

	@JsonProperty("MyHomeTown")
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	@JsonProperty("MyOtherTown")
	public String getName2() {
		return name2;
	}

	@JsonProperty("MyOtherTown")
	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	@JsonProperty("tempDifference")
	public Integer getTempDiff() {
		return tempDiff;
	}

	@JsonProperty("tempDifference")
	public void setTempDiff(Integer tempDiff) {
		this.tempDiff = tempDiff;
	}
	
	@JsonProperty("MyHomeTownLocation")
	public Coord getCoord1() {
		return coord1;
	}

	@JsonProperty("MyHomeTownLocation")
	public void setCoord1(Coord coord1) {
		this.coord1 = coord1;
	}

	@JsonProperty("MyOtherTownLocation")
	public Coord getCoord2() {
		return coord2;
	}

	@JsonProperty("MyOtherTownLocation")
	public void setCoord2(Coord coord2) {
		this.coord2 = coord2;
	}
	
	@JsonProperty("itsRainingIn")
	public String getItsRainingIn() {
		return itsRainingIn;
	}

	@JsonProperty("itsRainingIn")
	public void setItsRainingIn(String itsRainingIn) {
		this.itsRainingIn = itsRainingIn;
	}
	
	@JsonProperty("sunriseDiff")
	public Integer getSunriseDiff() {
		return sunriseDiff;
	}

	@JsonProperty("sunriseDiff")
	public void setSunriseDiff(Integer sunriseDiff) {
		this.sunriseDiff = sunriseDiff;
	}
	
	@JsonProperty("distance")
	public Integer getDist() {
		return sunriseDiff;
	}

	@JsonProperty("distance")
	public void setDist(Integer dist) {
		this.dist = dist;
	}	
	

}
