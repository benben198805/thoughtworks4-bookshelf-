package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookShelfController{

    @Autowired
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService)
    {
        this.bookService=bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView queryBooks() {

        ModelMap model = new ModelMap();
        model.put("books", bookService.findAll());
        return new ModelAndView("books", model);

    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.GET)
    public ModelAndView getBook(@PathVariable String isbn) {

        ModelMap model = new ModelMap();
        model.put("book", bookService.findByIsbn(isbn));
        return new ModelAndView("book", model);

    }

    @RequestMapping(value = "book/new", method = RequestMethod.GET)
    public ModelAndView createBook() {
        Book book=new Book();

        ModelMap model = new ModelMap();
        model.put("book", book);

        return new ModelAndView("newBook", model);
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        Book existBook=bookService.findByIsbn(book.getIsbn());
        if(existBook==null){
            bookService.create(book);
        }
        else
        {
            bookService.edit(book);
        }

        return "redirect:/";
    }



    @RequestMapping(value = "book/edit/{isbn}", method = RequestMethod.GET)
    public ModelAndView modifyBook(@PathVariable String isbn) {
        Book book=bookService.findByIsbn(isbn);

        ModelMap model = new ModelMap();
        model.put("book", book);

        return new ModelAndView("modifyBook", model);
    }


    @RequestMapping(value = "book/delete/{isbn}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);

        return "redirect:/";
    }


    @RequestMapping(value = "book/page/{pageIndex}/{pageSize}", method = RequestMethod.GET)
    public ModelAndView findBookByPage(@PathVariable String pageIndex,@PathVariable String pageSize) {
        Pageable pageable=new PageRequest(Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
        Page<Book> books=bookService.findBookByPage(pageable);
        int[] pages={books.getNumber(),books.getTotalPages()};

        ModelMap model = new ModelMap();
        model.put("books", books);
        model.put("pages", pages);

        return new ModelAndView("bookPage", model);
    }
}
