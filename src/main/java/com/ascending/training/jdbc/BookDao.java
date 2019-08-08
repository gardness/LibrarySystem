package com.ascending.training.jdbc;

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

                Book book = new Book(id, title, category, rental_price, status);
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
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "INSERT INTO books (id, title, category, rental_price, status) " +
                    "VALUES (?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getCategory());
            ps.setDouble(4, book.getRentalPrice());
            ps.setBoolean(5, book.getStatus());

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
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "UPDATE books SET title=?, category=?, rental_price=?, status=? WHERE id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getCategory());
            ps.setDouble(3, book.getRentalPrice());
            ps.setBoolean(4, book.getStatus());
            ps.setLong(5, book.getId());

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

    public int delete(long id) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "DELETE FROM books WHERE id=?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

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

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooks();

        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}
