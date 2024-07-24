package com.homie.Homie.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

public abstract class Locale {
    protected String id;
    protected String address;
    protected String description;
    @JsonAlias({"square_meters"})
    protected Integer squareMeters;
    protected String type;

    public Locale() {
    }

    public Locale(String id, String address, String description, Integer squareMeters, String type) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.squareMeters = squareMeters;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
