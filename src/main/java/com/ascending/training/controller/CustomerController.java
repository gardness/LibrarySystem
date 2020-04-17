package com.ascending.training.controller;

import com.amazonaws.services.dynamodbv2.xspec.NULL;
import com.ascending.training.model.Customer;
import com.ascending.training.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity getCustomer(@PathVariable String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);
        String msg = "Customer " + customerName + " doesn't exist.";
        ResponseEntity responseEntity = responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(msg);

        if (customer != null) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(customer);
            logger.info("Successfully get customer " + customerName);
        } else {
            logger.debug(msg);
        }

        return responseEntity;
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        String msg = "Customer " + customer.getName() + " has been created.";
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_CREATED).body(msg);
        long isSuccess = customerService.save(customer);

        if (isSuccess == 0) {
            msg = "Cannot create customer " + customer.getName() + ".";
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(msg);
            logger.debug(msg);
        } else {
            logger.info(msg + " " + customer.toString());
        }

        return responseEntity;
    }

    @PutMapping(value = "/{customerId}", consumes = "application/json")
    public ResponseEntity updateCustomer(@PathVariable long customerId, @RequestBody Customer customer) {
        Customer newCustomer = new Customer(customerId, customer.getName(), customer.getAddress());
        String msg = "Customer " + customer.getName() + " has been updated.";
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
        boolean isSuccess = customerService.update(newCustomer);

        if (!isSuccess) {
            msg = "Cannot update. Customer " + customer.getName() + " does not exist.";
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(msg);
            logger.debug(msg);
        } else {
            logger.info(msg);
        }

        return responseEntity;
    }

    @DeleteMapping(value = "/{customerName}")
    public ResponseEntity deleteCustomer(@PathVariable String customerName) {
        String msg = "Customer " + customerName + " was deleted.";
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).body(msg);
        boolean isSuccess = customerService.delete(customerName);

        if (!isSuccess) {
            msg = "Cannot delete. Customer " + customerName + " does not exist.";
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(msg);
            logger.debug(msg);
        } else {
            logger.info(msg);
        }

        return responseEntity;
    }
}
