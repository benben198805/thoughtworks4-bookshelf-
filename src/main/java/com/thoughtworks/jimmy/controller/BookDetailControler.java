package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.repository.BookRepository;

/**
 * Created by ben on 15-12-12.
 */
public class BookDetailControler {

    private BookRepository bookRepository = new BookRepository();


//    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET)
//    public ModelAndView queryBooks(@PathVariable String isbn) {
//        ModelMap model = new ModelMap();
//
//        Book book=bookRepository.findOne(isbn);
//        model.put("book",book);
//
//        return new ModelAndView("book", model);
//
//    }
//
//    @RequestMapping(value = "/book/new", method = RequestMethod.GET)
//    public ModelAndView newwBooks() {
//        ModelMap model = new ModelMap();
//        return new ModelAndView("newBook", model);
//
//    }

//
//    @RequestMapping(value = "/book/", method = RequestMethod.POST)
//    public ModelAndView saveBooks(@) {
//        ModelMap model = new ModelMap();
//        return new ModelAndView("newBook", model);
//
//    }

}
