# NavDemo

Java Spring Boot backend that provides indoor navigation instructions using Graphhopper 🗺. Designed to work alongside a client 
android app which can be found [here](https://github.com/billk97/Indoor-Tracking-App).

# Getting Started

Add your maps under the `resources/maps` directory. `<map-name>.osm` has to exist for Graphhopper to work. The `.geojson` version of the file is only used for the testing frontend. You also need to set said file name to be the one that's used under `application.properties`, for example:
```
spring.mvc.view.prefix= /WEB-INF/jsp/
spring.mvc.view.suffix= .jsp
map.name=nameGoesHereWithoutSuffix
```
The suffixes are added in each method. The project includes a test map of AUEB, named `test2aueb`, so you can run the project from the get go. You do not need to set up an actual database since the project is currently using a runtime DB (Apache Derby).

# Seeing it work

After running the project you can hit up `localhost:8081/` and see a little testing environment, where you can pick POIs and see the navigation instructions provided.

# Endpoints

The project implements the SwaggerUI library, so you can always hit up `http://localhost:8081/swagger-ui/` to view up to date documentation of existing endpoints and models.


The endpoints you're most likely to actually be using are the following:

`GET /`: provides a simple web-based frontend for testing. Source and destination coordinates (as well as dropdown names) are provided automatically, just make sure your nodes have the `poi=yes` attribute (in case you are using a custom map and not the default one).

`/access-point`: 📶 This is a REST endpoint, which means there are `GET` and `POST` endpoints. There's no need for editing or deleting for now. Since this is REST, `GET /access-point/<id>` is also declared. By default there are not APs.
**Example for POST:**  
The url should look like this 
> https://localhost:8081/access-point  

and in the http body add the Json form of an accessPoint Object
~~~json
{
	"ssid":"bil2l",
	"bssid":"1232",
	"level":-34,
	"h":4.5,
	"x":2.0,
	"y":1.2
}
~~~

`/poi`: 🚩 Similar to the above endpoint, you can `GET` and `POST` specific Points of Interest. By default it is populated with nodes from that have the `poi=yes` tag in the `map.geojson` file. `GET` requests to `/poi` return an array of designated POIs.

`/nav`: 🧭 The "navigation"  endpoint. You can `POST` source and destination coordinates and receive instructions. The `/` endpoint makes use of this and `/poi` to fetch the available POIs and then use their coordinates to fetch the instructions.

Example:

If you POST the following to `/nav`...

```json
 {
  "destLat": 37.9937662109,
  "destLon": 23.73204655878,
  "srcLat": 37.99445266073,
  "srcLon": 23.73243212633
}
```

Then the response will be a list of Instructions that you can use as you deem fit

```json
{
  "distance": 125,
  "instructions": [
    {
      "sign": 0,
      "name": "corridor-to-d22",
      "distance": 6.953,
      "time": 5006,
      "angle": "south-east"
    },
    {
      "sign": 2,
      "name": "main-to-derigny",
      "distance": 53.462,
      "time": 38489,
      "angle": "south-west"
    },
    {
      "sign": 4,
      "name": "",
      "distance": 0,
      "time": 0,
      "points": [
        {
          "lat": 37.993766239258846,
          "lon": 23.732046507895703
        }
      ]
    }
  ],
  "time": 90186
}
```
