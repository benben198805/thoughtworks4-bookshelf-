package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;


public class BookServiceImplTest {
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookRepository=new BookRepository();
        bookService=new BookServiceImpl(bookRepository);
    }

    @Test
    public void should_return_all_books() {
        Iterable<Book> books=bookService.findAll();

        for (Book book:books)
        {
            Assert.assertThat(book,is(bookRepository.BOOKS_MAP.get(book.getIsbn())));
        }
    }

    @Test
    public void should_return_CleanCode_when_find_by_ISBN_9780132350884()
    {
        Book book=bookService.findByIsbn("9780132350884");

        Assert.assertThat(book,is(bookRepository.BOOKS_MAP.get("9780132350884")));
    }

    @Test
    public void should_return_null_when_find_by_ISBN_not_in_repository()
    {
        Book book=bookService.findByIsbn("1111111");

        assertNull(book);
    }

    @Test
    public void should_success_when_save_newbook()
    {
        Book newBook =new Book("1111111","The New Book","New Author",10.0);

        bookService.saveBook(newBook);

        Assert.assertThat(bookRepository.BOOKS_MAP.get("1111111"),is(newBook));
    }


    @Test
    public void should_no_effect_when_save_exist_book()
    {
        Book newBook =new Book("9780201485677", "Refactoring", "Martin Fowler", 64.99);

        bookService.saveBook(newBook);

        Iterable<Book> books=bookService.findAll();
        int bookNumber=0;
        for(Book book:books)
        {
            bookNumber=book.getIsbn()=="9780201485677"?bookNumber+1:bookNumber;
        }
        Assert.assertThat(bookNumber,is(1));
    }



    @Test
    public void should_success_when_delete_exist_book()
    {
        boolean isDel= bookService.deleteBook("9780201485677");

        assertNull(bookRepository.BOOKS_MAP.get("9780201485677"));
        Assert.assertThat(isDel,is(true));
    }


    @Test
    public void should_no_effect_when_delete_no_exist_book()
    {
        Book modifiedBook=new Book("12113", "The Art", "Raymond", 40.0);

        boolean isDel= bookService.deleteBook("12113");

        assertNull(bookRepository.BOOKS_MAP.get("12113"));
        Assert.assertThat(isDel,is(false));
    }

    @Test
    public void should_success_when_modify_exist_book()
    {
        Book modifiedBook=new Book("9780131429017", "The Art", "Raymond", 40.0);

        boolean isMod=bookService.modifyBook(modifiedBook);


        Assert.assertThat(modifiedBook,is(bookRepository.BOOKS_MAP.get("9780131429017")));
        Assert.assertThat(isMod,is(true));
    }



    @Test
    public void should_no_effect_when_modify_no_exist_book()
    {
        Book modifiedBook=new Book("12113", "The Art", "Raymond", 40.0);

        boolean isMod=bookService.modifyBook(modifiedBook);

        assertNull(bookRepository.BOOKS_MAP.get("12113"));
        Assert.assertThat(isMod,is(false));
    }

}