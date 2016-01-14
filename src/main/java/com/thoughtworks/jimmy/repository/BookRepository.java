package com.thoughtworks.jimmy.repository;

import com.thoughtworks.jimmy.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitle(String title);
    Page<Book> findAll(Pageable pageable);
}
