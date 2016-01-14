package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;

import java.util.List;

public interface BookService {

    Iterable<Book> findAll();

    Book findByIsbn(String isbn);

    void create(Book book);

    void delete(String isbn);

    void edit(Book book);

    List<Book> search(String searchStr);
    List<Book> getBookByTitle(String title);
}
