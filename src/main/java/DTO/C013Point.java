package DTO;

public class C013Point {
    private LatLon latLon;
    private int gray;
    private int briTemp;

    public C013Point(LatLon latLon, int gray, int briTemp){
        this.latLon = latLon;
        this.gray = gray;
        this.briTemp = briTemp;
    }

    public double getLat(){
        return this.latLon.getLat();
    }

    public double getLon(){
        return this.latLon.getLon();
    }

    public int getBriTemp(){
        return this.briTemp;
    }

}
