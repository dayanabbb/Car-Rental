package com.company.entities;

public class Car {
    private int id;
    private int year;
    private String brand;
    private String model;
    private String type;
    private String color;
    private int owner_id;
    private int pricePerDay;


    public Car(){

    }
    public Car(int id, int year, String brand, String model, String type, String color, int pricePerDay) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }

    public Car(int id, int year, String brand, String model, String type, String color, int pricePerDay, int owner_id) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.owner_id = owner_id;
    }
    public Car(int year, String brand, String model, String type, String color, int pricePerDay) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }
    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }


    public String getType() {
        return type;
    }
    public int getOwner_id() {
        return owner_id;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
