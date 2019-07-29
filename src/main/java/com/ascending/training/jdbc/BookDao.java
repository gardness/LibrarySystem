package com.ascending.training.jdbc;

import com.ascending.training.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/mylibrarysystem_db";
    static final String USER = "admin";
    static final String PASS = "12345678";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Connecting to database ...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement ...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from book";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String category = rs.getString("category");
                double rental_price = rs.getDouble("rental_price");
                Boolean status = rs.getBoolean("status");

                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setCategory(category);
                book.setRental_price(rental_price);
                book.setStatus(status);
                books.add(book);
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

        logger.debug("Print logger_debug!");
        logger.info("Print logger_info!");

        return books;
    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooks();

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

}
