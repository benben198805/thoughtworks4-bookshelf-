package com.thoughtworks.jimmy.repository;

import com.thoughtworks.jimmy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitle(String title);
}
