package com.homie.Homie.models;

public class RentingOutLocale extends Locale {
    private Integer fromMonthlyCost;
    private Integer toMonthlyCost;

    public RentingOutLocale() {};

    public RentingOutLocale(String id, String address, String description, Integer squareMeters, String type, Integer fromMonthlyCost, Integer toMonthlyCost) {
        super(id, address, description, squareMeters, type);
        this.fromMonthlyCost = fromMonthlyCost;
        this.toMonthlyCost = toMonthlyCost;
    }

    public Integer getFromMonthlyCost() {
        return fromMonthlyCost;
    }

    public void setFromMonthlyCost(Integer fromMonthlyCost) {
        this.fromMonthlyCost = fromMonthlyCost;
    }

    public Integer getToMonthlyCost() {
        return toMonthlyCost;
    }

    public void setToMonthlyCost(Integer toMonthlyCost) {
        this.toMonthlyCost = toMonthlyCost;
    }
}
