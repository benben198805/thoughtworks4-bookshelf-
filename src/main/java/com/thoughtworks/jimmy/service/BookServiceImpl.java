package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findOne(isbn);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    @Override
    public boolean deleteBook(String isbn) {
        if (bookRepository.findOne(isbn) != null) {
            bookRepository.deleteBook(isbn);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean modifyBook(Book book) {
        if (bookRepository.findOne(book.getIsbn()) != null) {
            bookRepository.deleteBook(book.getIsbn());
            bookRepository.saveBook(book);
            return true;
        }
        else
        {
            return false;
        }
    }


}