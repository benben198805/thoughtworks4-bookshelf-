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
    public void saveBook(Book book){ bookRepository.saveBook(book);}

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

    @Override
    public void modifyBook(Book book) {
        bookRepository.deleteBook(book.getIsbn());
        bookRepository.saveBook(book);
    }
}
