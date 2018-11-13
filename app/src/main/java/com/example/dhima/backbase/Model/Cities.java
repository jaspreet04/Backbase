package com.example.dhima.backbase.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("coord")
    @Expose
    private Coord coord;

    /**
     * No args constructor for use in serialization
     *
     */
    public Cities() {
    }

    /**
     *
     * @param coord
     * @param id
     * @param name
     * @param country
     */
    public Cities(String country, String name, Integer id, Coord coord) {
        super();
        this.country = country;
        this.name = name;
        this.id = id;
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

}