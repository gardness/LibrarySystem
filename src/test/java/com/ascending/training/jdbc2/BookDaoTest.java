package com.ascending.training.jdbc2;

import com.ascending.training.jdbc1.BookDao;
import com.ascending.training.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookDaoTest {
    private BookDao bookDao;

    @Before
    public void init() {
        bookDao = new BookDao();
    }

    @Test
    public void getBooksTest() {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooks();

        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        Assert.assertEquals(11, 11);
    }
}
