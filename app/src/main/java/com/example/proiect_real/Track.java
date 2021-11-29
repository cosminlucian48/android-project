package com.example.proiect_real;


import java.io.Serializable;

public class Track implements Serializable {
    private String name;
    private String city;
    private String trackLength;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.city = country;
    }

    public String getLength() {
        return trackLength;
    }

    public void setLength(String length) {
        this.trackLength = length;
    }

}
