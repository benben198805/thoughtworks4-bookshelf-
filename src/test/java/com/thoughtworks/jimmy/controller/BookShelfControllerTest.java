package com.thoughtworks.jimmy.controller;


import com.thoughtworks.jimmy.repository.BookRepository;
import com.thoughtworks.jimmy.service.BookService;
import com.thoughtworks.jimmy.service.BookServiceImpl;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration
//@IntegrationTest("server.port:8080")
//@Ignore
public class BookShelfControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();
    }

}