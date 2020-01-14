package com.ascending.training.controller;

import com.ascending.training.model.Customer;
import com.ascending.training.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "", produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/{customerName}", produces = "application/json")
    public Customer getCustomer(@PathVariable String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);

        return customer;
    }

    @PostMapping(value = "", consumes = "application/json")
    public String createCustomer(@RequestBody Customer customer) {
        logger.debug(String.format("Customer : %s", customer.toString()));

        String msg = "The customer has been created.";
        long isSuccess = customerService.save(customer);

        if (isSuccess == 0) {
            msg = "The customer has not been created.";
        }

        return msg;
    }

    @PutMapping(value = "/{customerId}", consumes = "application/json")
    public String updateCustomer(@PathVariable long customerId, @RequestBody Customer customer) {
        logger.debug(String.format("Customer ID : %d, Customer : %s", customerId, customer.toString()));

        Customer newCustomer = new Customer(customerId, customer.getName(), customer.getAddress());
        String msg = "The customer has been updated.";
        boolean isSuccess = customerService.update(newCustomer);

        if (!isSuccess) {
            msg = "The customer has not been updated.";
        }

        return msg;
    }

    @DeleteMapping(value = "/{customerName}", consumes = "application/json")
    public String deleteCustomer(@PathVariable String customerName) {
        logger.debug("Customer name : " + customerName);

        String msg = "The customer was deleted.";
        boolean isSuccess = customerService.delete(customerName);
        if (!isSuccess) {
            msg = "The customer was not deleted.";
        }

        return msg;
    }

}
