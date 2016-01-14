package com.thoughtworks.jimmy;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by ben on 15-12-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:8080")
public class SpringBootWebApplicationTest {
    @Autowired
    private EmbeddedWebApplicationContext webApplicationContext;
    @Autowired
    private BookRepository bookRepository;
    @Value("${local.server.port}")
    private int port;

    private MockMvc mockMvc;
    private RestTemplate restTemplate = new TestRestTemplate();



    @Test
    public void should_return_all_books() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Book book = restTemplate.getForObject("127.0.0.1:" + port +"/booksJson/9780201485677", Book.class);
        assertNotNull(book);
        assertThat(book.getName(),is("Refactoring"));
    }


}