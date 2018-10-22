package main.java;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;
import java.io.IOException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

    @SneakyThrows
    public static void main(String[] args) throws IOException, UnirestException {
        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
        Elements cities = doc.select("table tr");

        City[] parsedCities = new City[cities.size()];
        int counter = 0;
        Scanner scan = new Scanner(System.in);
        String ent_city = scan.nextLine();
        for (Element city : cities)
        {
            City myCity = City.parse(city);
            if (myCity != null)
            {
                counter++;
                parsedCities[counter] = myCity;
                if (parsedCities[counter].getName().toLowerCase().equals(ent_city.toLowerCase()))
                {
                    System.out.print(WeatherForecaster.forecast(parsedCities[counter]));
                }
            }
        }


    }
}
