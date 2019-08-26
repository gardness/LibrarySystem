package com.ascending.training.controller;

import com.ascending.training.model.Book;
import com.ascending.training.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/books"})
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

//    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(value = "", produces = "application/json")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

//    @RequestMapping(value = "/{bookTitle}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(value = "/{BookTitle}", produces = "application/json")
    public List<Book> getBook(@PathVariable String bookTitle) {
        Book book = bookService.getBookByTitle(bookTitle);

        if (book != null) {
            return bookService.getBooks();
        }

        return null;
    }

    @RequestMapping(value = "/")
    public String createBook(@RequestBody Book book) {
        logger.debug(String.format("Book : %s", book.toString()));

        String msg = "The book has been created.";
        long isSuccess = bookService.save(book);

        if (isSuccess == 0) {
            msg = "The book has not been created";
        }

        return msg;
    }

}
