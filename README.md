# Parse a weather service

In this coding task I implement the required weather service with the following steps:

* Connect to the OWM URL for both cities
* Retrieve JSON string
* Save retrieved JSON string in an object of class OwmModel, which contains the parameters based on the JSON schema
* Calculate the distance, differences of temperatures, and sunrise times
* Construct an output JSON object based on a custom class which contains the required JSON attributes
* Print the output JSON string on the webpage
* In case of any error, the webpage will display error 500 with the value of the output JSON object
