package com.skillbox.fibonacci;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
        (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    @Autowired
    private FibonacciRepository repository;


    @Autowired
    private TestRestTemplate template = new TestRestTemplate();

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    private void fillInDatabase() {
        FibonacciNumber fibonacciNumber1 = new FibonacciNumber(1, 1);
        FibonacciNumber fibonacciNumber3 = new FibonacciNumber(3, 2);
        FibonacciNumber fibonacciNumber10 = new FibonacciNumber(10, 55);
        repository.save(fibonacciNumber1);
        repository.save(fibonacciNumber3);
        repository.save(fibonacciNumber10);
    }

    @AfterEach
    public void clearDatabase() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET returns correct response if FibonacciNumber is already in the database")
    public void testGetNumberIfExists() {

        assertEquals(55, repository.findByIndex(10).get().getValue());

        ResponseEntity<FibonacciNumber> response =
                template.getRestTemplate().getForEntity("http://localhost:" + port + "/fibonacci/10",
                        FibonacciNumber.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(55, response.getBody().getValue());
        assertEquals(10, response.getBody().getIndex());
    }

    @Test
    @DisplayName("GET returns correct response if FibonacciNumber originally is NOT in the database")
    public void testGetNumberIfNotExist() {

        assertEquals(Optional.empty(), repository.findByIndex(6));

        ResponseEntity<FibonacciNumber> response =
                template.getRestTemplate().getForEntity("http://localhost:" + port + "/fibonacci/6",
                        FibonacciNumber.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(8, response.getBody().getValue());
        assertEquals(6, response.getBody().getIndex());
    }

    @Test
    @DisplayName("GET returns error and error message if index is < 1")
    public void testGetNumberIfWrong() {
        ResponseEntity<String> response = template
                .getRestTemplate()
                .getForEntity("http://localhost:" + port + "/fibonacci/0",
                        String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getStatusCode().is4xxClientError());
        assertTrue(response.getBody().equals("Index should be greater or equal to 1"));
    }
}
