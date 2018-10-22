package main.java;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Coordinates {
    static String lon;
    static String lat;

    Coordinates(String url) throws java.io.IOException{
        Document doc = Jsoup.connect(url).get();
        Element cord_opt = doc.select(".mw-kartographer-maplink").first();
        lon = cord_opt.attr("data-lon");
        lat = cord_opt.attr("data-lat");
    }
    static String getLon() {return lon;}
    static String getLat() {return lat;}
}