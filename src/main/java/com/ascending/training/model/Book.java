package com.ascending.training.model;

public class Book {
    private long id;
    private String title;
    private String category;
    private double rental_price;
    private Boolean status = true;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getRental_price() {
        return rental_price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRental_price(double rental_price) {
        this.rental_price = rental_price;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
