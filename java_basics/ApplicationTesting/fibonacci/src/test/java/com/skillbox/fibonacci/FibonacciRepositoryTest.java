package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    FibonacciNumber fibonacciNumber1;
    FibonacciNumber fibonacciNumber3;
    FibonacciNumber fibonacciNumber10;

    int fibonacciValue1 = 1;
    int fibonacciValue3 = 2;
    int fibonacciValue10 = 55;


    @BeforeEach
    public void setup(){
        fibonacciNumber1 = new FibonacciNumber(1, 1);
        fibonacciNumber3 = new FibonacciNumber(3, 2);
        fibonacciNumber10 = new FibonacciNumber(10, 55);
    }

    public void saveFibonacciesToDatabase(){

        repository.save(fibonacciNumber1);
        repository.save(fibonacciNumber3);
        repository.save(fibonacciNumber10);
        entityManager.flush();
        entityManager.detach(fibonacciNumber1);
        entityManager.detach(fibonacciNumber3);
        entityManager.detach(fibonacciNumber10);
    }

    @AfterEach
    public void clearDatabase() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Test if 3 items inserted, repository has 3 items in it")
    public void testFibonacciRepository_Save_RightAmount(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
    }

    @Test
    @DisplayName("Test if 3 items inserted, repository has SAME 3 items in it")
    public void testFibonacciRepository_Save_SameItems(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1 OR index = 3 OR index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(3, actual.size());
        assertEquals(fibonacciValue1, actual.get(0).getValue());
        assertEquals(fibonacciValue3, actual.get(1).getValue());
        assertEquals(fibonacciValue10, actual.get(2).getValue());
    }

    @Test
    @DisplayName("Test if 3 items inserted, and then the same item inserted a few times, " +
            "repository has SAME 3 items in it, originally inserted")

    public void testFibonacciRepository_Save_NoDuplicateItems(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());

        repository.save(fibonacciNumber3);
        repository.save(fibonacciNumber3);
        repository.save(fibonacciNumber3);
        entityManager.flush();
        entityManager.detach(fibonacciNumber3);

        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());


        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1 OR index = 3 OR index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(fibonacciValue1, actual.get(0).getValue());
        assertEquals(fibonacciValue3, actual.get(1).getValue());
        assertEquals(fibonacciValue10, actual.get(2).getValue());
    }

    @Test
    @DisplayName("Test if 3 items inserted, then 2 deleted, repository has 1 item left")
    public void testFibonacciRepository_Delete_RightAmount(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());
        repository.delete(fibonacciNumber1);
        repository.delete(fibonacciNumber3);
        entityManager.flush();
        assertEquals(1, repository.findAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    @DisplayName("Test if 3 items inserted, then 2 deleted, repository has 1 SPECIFIC item left")
    public void testFibonacciRepository_Delete_SameItems(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();

        repository.delete(fibonacciNumber1);
        repository.delete(fibonacciNumber3);
        entityManager.flush();

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(fibonacciValue10, actual.get(0).getValue());
    }

    @Test
    @DisplayName("Test FindByIndex() repository function")
    public void testFibonacciRepository_FindByIndex(){
        assertEquals(0, repository.findAll().spliterator().getExactSizeIfKnown());
        saveFibonacciesToDatabase();
        assertEquals(3, repository.findAll().spliterator().getExactSizeIfKnown());

        assertEquals(fibonacciValue1, repository.findByIndex(1).get().getValue());
        assertEquals(fibonacciValue3, repository.findByIndex(3).get().getValue());
        assertEquals(fibonacciValue10, repository.findByIndex(10).get().getValue());

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1 OR index = 3 OR index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(actual.get(0).getValue(), repository.findByIndex(1).get().getValue());
        assertEquals(actual.get(1).getValue(), repository.findByIndex(3).get().getValue());
        assertEquals(actual.get(2).getValue(), repository.findByIndex(10).get().getValue());
    }
}
