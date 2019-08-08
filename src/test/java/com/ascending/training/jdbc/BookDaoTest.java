package com.ascending.training.jdbc;

import com.ascending.training.model.Book;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookDaoTest {
    private static BookDao bookDao;
    private static Logger logger = LoggerFactory.getLogger(BookDaoTest.class);

    @BeforeClass
    public static void beforeClass() {
        bookDao = new BookDao();
    }

    @AfterClass
    public static void afterClass() {
        bookDao = null;
    }

    // Currently not giving id can cause duplicate key value error
    @Ignore
    @Test
    public void save() {
        Book fNewBook = new Book(1234, "Thinking in Java", "Technology", 47.27, false);
        int fRet = bookDao.save(fNewBook);
        Book sNewBook = new Book(2345, "Introduction to Algorithm", "Technology", 64.04, false);
        int sRet = bookDao.save(sNewBook);

        Assert.assertEquals(1, fRet);
        Assert.assertEquals(1, sRet);

        logger.info("First Test!");
    }

    @Ignore
    @Test
    public void getBooks() {
        List<Book> books = bookDao.getBooks();

        for (Book book : books) {
            System.out.println(book);
        }

//        books.forEach(bk -> logger.info(bk));


        Assert.assertNotEquals(0, books.size());

        logger.info("Second Test!");
    }

    @Test
    public void delete() {
        int ret = bookDao.delete(2345);

        Assert.assertEquals(1, ret);

        logger.info("Third Test!");
    }

    @Ignore
    @Test
    public void update() {
        Book updatedBook = new Book(1234, "Advanced Programming in the UNIX environment", "Technology", 43.95, true);
        int ret = bookDao.update(updatedBook);

        Assert.assertEquals(1, ret);

        logger.info("Fourth Test!");
    }
}
