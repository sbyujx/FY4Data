package DTO;

public class LatLon {
    private double lat;
    private double lon;
    public LatLon(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }
}
