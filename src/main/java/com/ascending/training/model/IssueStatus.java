package com.ascending.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "issue_statuses")
public class IssueStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @JsonIgnore
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JsonIgnore
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    public IssueStatus(){}

    public IssueStatus(String issueDate, String returnDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            this.issueDate = simpleDateFormat.parse(issueDate);
            this.returnDate = simpleDateFormat.parse(returnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.id = id;
    }

    public IssueStatus(long id, String issueDate, String returnDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            this.issueDate = simpleDateFormat.parse(issueDate);
            this.returnDate = simpleDateFormat.parse(returnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.id = id;
    }

    public IssueStatus(String issueDate, String returnDate, Customer customer, Book book) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            this.issueDate = simpleDateFormat.parse(issueDate);
            this.returnDate = simpleDateFormat.parse(returnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.customer = customer;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format(id + ", " + issueDate + ", " + returnDate + ", " + customer + ", " + book);
    }
}
