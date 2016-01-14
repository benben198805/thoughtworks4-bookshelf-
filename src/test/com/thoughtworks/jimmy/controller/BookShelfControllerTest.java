package com.thoughtworks.jimmy.controller;


import com.thoughtworks.jimmy.repository.BookRepository;
import com.thoughtworks.jimmy.service.BookService;
import com.thoughtworks.jimmy.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BookShelfControllerTest {
    @Test
    public void should_return_all_books_when_access_root_url() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].isbn",is("9780131429017")))
                .andExpect(jsonPath("$[0].name",is("The Art of UNIX Programming")))
                .andExpect(jsonPath("$[0].author",is("Eric S. Raymond")))
                .andExpect(jsonPath("$[0].price",is(39.99)))
                .andExpect(jsonPath("$[1].isbn",is("9780201485677")))
                .andExpect(jsonPath("$[1].name",is("Refactoring")))
                .andExpect(jsonPath("$[1].author",is("Martin Fowler")))
                .andExpect(jsonPath("$[1].price",is(64.99)))
                .andExpect(jsonPath("$[2].isbn",is("9780132350884")))
                .andExpect(jsonPath("$[2].name",is("Clean Code")))
                .andExpect(jsonPath("$[2].author",is("Robert C. Martin")))
                .andExpect(jsonPath("$[2].price",is(35.44)))
                .andDo(print())
                .andReturn();
    }



    @Test
    public void should_return_9780131429017_book_when_find_book_9780131429017() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/book/9780131429017"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",is("9780131429017")))
                .andExpect(jsonPath("$.name",is("The Art of UNIX Programming")))
                .andExpect(jsonPath("$.author",is("Eric S. Raymond")))
                .andExpect(jsonPath("$.price",is(39.99)))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void should_return_flag_1_book_when_save_book_1111111() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();
        String request="{\"isbn\":\"1111111\",\"name\":\"The New Book\",\"author\":\"New Author\",\"price\":10.0}";

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/book/save").content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(1)))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void should_return_flag_2_book_when_save_exist_book_9780131429017() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();
        String request="{\"isbn\":\"9780131429017\",\"name\":\"The New Book\",\"author\":\"New Author\",\"price\":10.0}";

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/book/save").content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(2)))
                .andDo(print())
                .andReturn();
    }



    @Test
    public void should_return_flag_1_book_when_delete_exist_book_9780131429017() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/book/delete/9780131429017"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(1)))
                .andDo(print())
                .andReturn();
    }



    @Test
    public void should_return_flag_0_book_when_delete_noExist_book_123() throws Exception {
        BookService bookService=new BookServiceImpl(new BookRepository());
        BookShelfController bookShelfController=new BookShelfController(bookService);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(bookShelfController).build();

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/book/delete/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(0)))
                .andDo(print())
                .andReturn();
    }
}