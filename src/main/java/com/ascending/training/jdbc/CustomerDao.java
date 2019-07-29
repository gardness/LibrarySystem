package com.ascending.training.jdbc;

import com.ascending.training.model.Customer;

import java.sql.*;
import java.util.*;

public class CustomerDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/mylibrarysystem_db";
    static final String USER = "admin";
    static final String PASS = "12345678";

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Connecting to database ...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement ...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from customer";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String address = rs.getString("address");

                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setAddress(address);
                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return customers;
    }
}