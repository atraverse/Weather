package main.java;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class WeatherForecaster {


    public static String forecast(City city) throws UnirestException, java.io.IOException {
        String mycity = city.getUrl();
        String name = city.getName();
        String area = city.getarea();
        String numberOfCitizens = city.getnumberOfCitizens();
        String administrativeArea = city.getadministrativeArea();
        String yearOfFound = city.getyearOfFound();

        Coordinates coord = new Coordinates(mycity);

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://api.apixu.com/v1/current.json")
                .queryString("key", "2299d8c403fc4322b22102538181310")
                .queryString("q", coord.getLon() + "," + coord.getLat()).asJson();

        if (jsonResponse.getStatus() != 400) {
            JSONObject json = new JSONObject(jsonResponse.getBody());
            JSONObject data = json.getJSONArray("array").getJSONObject(0).getJSONObject("current");

            double temp = data.getDouble("temp_c");
            double feel_l = data.getDouble("feelslike_c");
            double cloud = data.getDouble("cloud");
            double wind = data.getDouble("wind_degree");
            String wind_dir = data.getString("wind_dir");
            double wind_kph = data.getDouble("wind_kph");
            double wind_mph = data.getDouble("wind_mph");
            double pressure = data.getDouble("pressure_in");
            double is_day=data.getDouble("is_day");
            double precip_in=data.getDouble("precip_in");
            double precip_mm=data.getDouble("precip_mm");
            double vis_km=data.getDouble("vis_km");
            double humidity=data.getDouble("humidity");
            double pressure_mb=data.getDouble("pressure_mb");
            return "City: "+ name +
                    "\nArea: "+ area +
                    "\nAdministrative Area: "+ administrativeArea +
                    "\nNumber of citizen: "+ numberOfCitizens +
                    "\nYear of found: "+ yearOfFound +
                    "\nTemperature: "+ temp +
                    "\nFeels like: " + feel_l +
                    "\nCloud: " + cloud+
                    "\nWind degree: " + wind+
                    "\nWind direction: " + wind_dir+
                    "\nWind kph: " + wind_kph+
                    "\nWind mph: " + wind_mph+
                    "\nPressure in: " + pressure+
                    "\nPressure mb: " + pressure_mb+
                    "\nDay/night: " + is_day+
                    "\nPrecip_in: " + precip_in+
                    "\nPrecip_mm: " + precip_mm+
                    "\nVis. km: " + vis_km+
                    "\nHimidity: " + humidity;

        }
        return "No information about this city";
    }

}
