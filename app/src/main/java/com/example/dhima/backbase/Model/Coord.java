package com.example.dhima.backbase.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

@SerializedName("lon")
@Expose
private Double lon;
@SerializedName("lat")
@Expose
private Double lat;

/**
 * No args constructor for use in serialization
 *
 */
public Coord() {
        }

/**
 *
 * @param lon
 * @param lat
 */
public Coord(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
        }

public Double getLon() {
        return lon;
        }

public void setLon(Double lon) {
        this.lon = lon;
        }

public Double getLat() {
        return lat;
        }

public void setLat(Double lat) {
        this.lat = lat;
        }

        }