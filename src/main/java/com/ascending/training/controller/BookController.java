package com.ascending.training.controller;

import com.ascending.training.model.Book;
import com.ascending.training.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/books"})
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

}
