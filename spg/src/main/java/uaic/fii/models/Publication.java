package uaic.fii.models;

public class Publication {
    // Publicatie: {(stationid,1);(city,"Bucharest");(temp,15);(rain,0.5);(wind,12);(direction,"NE");(date,2.02.2023)}

    private int stationId;
    private String city;
    private int temp;
    private double rain;
    private int wind;
    private String direction;
    private String date;

    public Publication(){}

    private Publication(int stationId, String city, int temp, double rain, int wind, String direction, String date) {
        this.stationId = stationId;
        this.city = city;
        this.temp = temp;
        this.rain = rain;
        this.wind = wind;
        this.direction = direction;
        this.date = date;
    }

    public Publication withStationId(int stationId) {
        this.stationId = stationId;
        return this;
    }

    public Publication withCity(String city) {
        this.city = city;
        return this;
    }

    public Publication withTemp(int temp) {
        this.temp = temp;
        return this;
    }

    public Publication withRain(double rain) {
        this.rain = rain;
        return this;
    }

    public Publication withWind(int wind) {
        this.wind = wind;
        return this;
    }

    public Publication withDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public Publication withDate(String date) {
        this.date = date;
        return this;
    }

    public Publication build() {
        return new Publication(stationId, city, temp, rain, wind, direction, date);
    }

    @Override
    public String toString() {
        return "Publication: {" +
                "(stationId," + stationId + ")" +
                ";(city,\"" + city + "\")" +
                ";(temp," + temp + ")" +
                ";(rain," + rain + ")" +
                ";(wind," + wind + ")" +
                ";(direction,\"" + direction + "\")" +
                ";(date," + date + ")" +
                '}';
    }
}
