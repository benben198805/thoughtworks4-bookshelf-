package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;

public interface BookService {

    Iterable<Book> findAll();

    Book findByIsbn(String isbn);

    void saveBook(Book book);

    boolean deleteBook(String isbn);
    boolean modifyBook(Book book);

}
