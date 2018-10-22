package main.java;
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

@Getter
@Setter
@ToString
class City {
    static String name;
    static String url;
    static String numberOfCitizens;
    static String yearOfFound;
    static String administrativeArea;
    static String area;

    private static final int INFO_SIZE = 6;

    static City parse(Element city) throws IOException {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {

            City myCity = new City();
            myCity.setName(info.get(1).select("a").get(0).text());
            myCity.setUrl("https://uk.wikipedia.org" + info.get(1).select("a").attr("href"));
            myCity.setadministrativeArea(info.get(2).text());
            myCity.setnumberOfCitizens(info.get(3).text().split("!")[0]);
            myCity.setyearOfFound(info.get(4).select("a").get(0).text());
            myCity.setarea(info.get(5).text());
            return myCity;
        }
        return null;
    }
    private void setName(String new_name) {this.name = new_name;}
    private void setUrl(String new_url) {this.url = new_url;}
    private void setadministrativeArea(String new_administrative_area) {this.administrativeArea = new_administrative_area;}
    private void setnumberOfCitizens(String new_number_of_citizens) {this.numberOfCitizens = new_number_of_citizens;}
    private void setyearOfFound(String new_year_of_found) {this.yearOfFound = new_year_of_found;}
    private void setarea(String new_area) {this.area = new_area;}

    static String getName() {return name;}
    static String getadministrativeArea() {return administrativeArea;}
    static String getnumberOfCitizens() {return numberOfCitizens;}
    static String getyearOfFound() {return yearOfFound;}
    static String getUrl() {return url;}
    static String getarea() {return area;}

}