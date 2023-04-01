package uaic.fii.generators;

import java.util.HashMap;
import java.util.Map;

public class CityGenerator {
    private static Map<Integer, String> cities;

    static {
        initCities();
    }

    private static void initCities(){
        cities = new HashMap<>();
        cities.put(1, "Bucharest");
        cities.put(2, "Cluj-Napoca");
        cities.put(3, "Iași");
        cities.put(4, "Constanța");
        cities.put(5, "Timișoara");
        cities.put(6, "Craiova");
        cities.put(7, "Oradea");
        cities.put(8, "Ploiești");
        cities.put(9, "Arad");

    }
    public static String getRandomCity(){
        int randomCityMapId = NumberGenerator.getRandomInt(1, cities.size());
        return cities.get(randomCityMapId);
    }
}
