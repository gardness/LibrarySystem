package com.ascending.training.jdbc1;

import com.ascending.training.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao {
    static final String dbURL = "jdbc:postgresql://localhost:5433/mylibrarysystem_db";
    static final String username = "admin";
    static final String password = "12345678";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM books";
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

        return books;
    }

    public int save(Book book) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            System.out.println("Connecting to database ...");
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "INSERT INTO books (id, title, category, rental_price, status) " +
                    "VALUES (?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, 1234);
            ps.setString(2, "Thinking in Java");
            ps.setString(3, "Technology");
            ps.setDouble(4, 47.27);
            ps.setBoolean(5, false);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return status;
    }

    public int update(Book book) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            System.out.println("Connecting to database ...");
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "UPDATE books SET title=?, category=?, rental_price=?, status=? WHERE id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "Thinking in Java");
            ps.setString(2, "Technology");
            ps.setDouble(3, 47.27);
            ps.setBoolean(4, false);
            ps.setLong(5, 1234);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return status;
    }

    public int delete(int id) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "DELETE FROM books WHERE id=?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, 1234);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return status;
    }
}
