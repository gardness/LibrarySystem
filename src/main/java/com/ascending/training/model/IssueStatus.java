package com.ascending.training.model;

import java.util.Date;

public class IssueStatus {
    private long id;
    private Date issueDate;
    private Date returnDate;
    private Customer customer;
    private Book book;

    public long getId() {
        return id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }



}
