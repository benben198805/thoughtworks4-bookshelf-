package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void should_return_4_books_when_search_Str() throws Exception {
        String searchStr="The";

        List<Book> books= Arrays.asList(
                new Book("1","The Soul of an Octopus","author1",10.0),
                new Book("1","The Testament of Mary","author1",10.0),
                new Book("1","The Things They Carried","author1",10.0),
                new Book("1","The Tiger’s Wife","author1",10.0)
        );

        when(bookRepository.findByTitleContaining(searchStr)).thenReturn(books);

        List<Book> result=bookServiceImpl.search(searchStr);

        assertThat(result,is(books));
    }




}