package manhthang.adididemo.Object;

import java.io.Serializable;

public class Location implements Serializable {

    private Double lat;
    private Double logt;
    private String nameLocation;
    private String nameDetailLocation;

    public Location() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLogt() {
        return logt;
    }

    public void setLogt(Double logt) {
        this.logt = logt;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getNameDetailLocation() {
        return nameDetailLocation;
    }

    public void setNameDetailLocation(String nameDetailLocation) {
        this.nameDetailLocation = nameDetailLocation;
    }
}
