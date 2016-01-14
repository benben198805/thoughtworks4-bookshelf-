package com.thoughtworks.jimmy;


import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Created by ben on 15-12-18.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class test {
    @Autowired
    BookRepository repository;

    Book newBook;

    @Before
    public void setUp() {
        newBook=new Book("123","123","123",12.0d);
    }

    @Test
    public void canFetchMickey() {
//        when().
//                get("/booksJson").
//        then().
//                statusCode(HttpStatus.ACCEPTED).
//                body("name", Matchers.is("Mickey Mouse")).
//                body("id", Matchers.is(mickeyId));
    }



}
