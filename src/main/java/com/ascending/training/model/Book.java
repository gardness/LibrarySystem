package com.ascending.training.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "rental_price")
    private double rentalPrice;

    @Column(name = "status")
    private Boolean status = true;

    public Book(){}

    public Book(long id, String title, String category, double rentalPrice, Boolean status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.rentalPrice = rentalPrice;
        this.status = status;
    }

    public Book(String title, String category, double rentalPrice, Boolean status) {
        this.title = title;
        this.category = category;
        this.rentalPrice = rentalPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRentalPrice(double rental_price) {
        this.rentalPrice = rental_price;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(id + ", " + title + ", " + category + ", " + rentalPrice + ", " + status);
    }
}
