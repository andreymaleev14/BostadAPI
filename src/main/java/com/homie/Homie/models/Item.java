package com.homie.Homie.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private String address;
    private String type;
    private Integer squareMeter;

    public Item() {
    };

    @JsonCreator
    public Item(@JsonProperty("address") String address, @JsonProperty("type") String type, @JsonProperty("square_meter") Integer squareMeter) {
        this.address = address;
        this.type = type;
        this.squareMeter = squareMeter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(Integer squareMeter) {
        this.squareMeter = squareMeter;
    }
}
