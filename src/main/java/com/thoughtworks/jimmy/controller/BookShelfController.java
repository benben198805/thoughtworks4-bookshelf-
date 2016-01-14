package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookShelfController {

    @Autowired
    private BookService bookService;

    public BookShelfController(BookService bookService) {
        this.bookService=bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Book> queryBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBook(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    @ResponseBody
    public int saveBook(@ModelAttribute("book") Book book) {
        Book existBook=bookService.findByIsbn(book.getIsbn());
        if(existBook==null){
            bookService.saveBook(book);
            return 1;
        }
        else
        {
            bookService.modifyBook(book);
            return 2;
        }
    }


    @RequestMapping(value = "book/delete/{isbn}", method = RequestMethod.GET)
    @ResponseBody
    public int deleteBook(@PathVariable String isbn) {
        if (bookService.deleteBook(isbn)) return 1;
        else return 0;
    }

}
