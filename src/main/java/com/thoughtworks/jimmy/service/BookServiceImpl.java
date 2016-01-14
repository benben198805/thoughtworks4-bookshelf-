package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
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
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(String isbn) {
        bookRepository.delete(isbn);
    }

    @Override
    public void edit(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> search(String searchStr) {
        return bookRepository.findByTitleContaining(searchStr);
    }
    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
