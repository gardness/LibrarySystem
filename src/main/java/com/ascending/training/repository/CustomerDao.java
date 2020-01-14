package com.ascending.training.repository;

import com.ascending.training.model.Customer;

import java.util.List;

public interface CustomerDao {
    long save(Customer customer);

    boolean update(Customer customer);

    boolean delete(String customerName);

    List<Customer> getCustomers();

    Customer getCustomerByName(String customerName);

    Customer getCustomerById(long customerId);
}
