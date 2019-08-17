package com.ascending.training.repository;

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
        bookDao = new BookDaoImpl();
    }

    @AfterClass
    public static void afterClass() {
        bookDao = null;
    }

    @Before
    public void before() {
        Book fNewBook = new Book("Thinking in Java", "Technology", 47.27, false);
        long fRet = bookDao.save(fNewBook);
        Book sNewBook = new Book("Advanced Programming in the UNIX environment", "Technology", 43.95, true);
        long sRet = bookDao.save(sNewBook);
    }

    @After
    public void after() {
        Boolean fret = bookDao.delete("Thinking in Java");
        Boolean sret = bookDao.delete("Advanced Programming in the UNIX environment");
    }

    @Test
    public void save() {
        Book newBook = new Book("Introduction to Algorithm", "Technology", 64.04, false);
        long ret = bookDao.save(newBook);
        Assert.assertNotNull(ret);

//        bookDao.delete("Introduction to Algorithm");
        logger.info("First Test!");
    }

    @Test
    public void getBooks() {
        List<Book> books = bookDao.getBooks();

        for (Book book : books) {
            System.out.println(book);
        }

        Assert.assertNotEquals(0, books.size());

        logger.info("Second Test!");
    }

    @Test
    public void delete() {
        Boolean ret = bookDao.delete("Advanced Programming in the UNIX environment");

        Assert.assertEquals(true, ret);

        logger.info("Third Test!");
    }

    @Test
    public void update() {
        Book updatedBook = new Book("Head First Java", "Technology", 34.08, true);
        Boolean ret = bookDao.update(updatedBook);

        Assert.assertEquals(true, ret);

        logger.info("Fourth Test!");
    }

    @Test
    public void getBookByTitle() {
        Book book = bookDao.getBookByTitle("Thinking in Java");
        System.out.println(book);

        Assert.assertNotNull(book);
    }

}
