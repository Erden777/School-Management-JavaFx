package sample;

import java.io.Serializable;

public class Car implements Serializable {
    private Long id;
    private String model;
    private int year;
    private String country;

    public Car() {}

    public Car(Long id, String model, int year, String country) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", country='" + country;
    }
}
