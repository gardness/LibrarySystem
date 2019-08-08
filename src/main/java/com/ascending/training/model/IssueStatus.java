package com.ascending.training.model;

import javax.persistence.*;
import java.util.Date;

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

//    @Column(name = "customer_id")
//    private Customer customer;
//
//    @Column(name = "book_id")
//    private Book book;

    public long getId() {
        return id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }



}
