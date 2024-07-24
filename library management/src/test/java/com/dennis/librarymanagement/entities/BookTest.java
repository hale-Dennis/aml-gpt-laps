package com.dennis.librarymanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1, "Programming with C++", "D. Malik", "Klay-Thompson", 2018, "978-0134685991", 5);
    }

    @Test
    public void testGetId() {
        assertEquals(1, book.getId());
    }

    @Test
    public void testSetId() {
        book.setId(2);
        assertEquals(2, book.getId());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Programming with C++", book.getTitle());
    }

    @Test
    public void testSetTitle() {
        book.setTitle("Java Concurrency in Practice");
        assertEquals("Java Concurrency in Practice", book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("D. Malik", book.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        book.setAuthor("Brian Goetz");
        assertEquals("Brian Goetz", book.getAuthor());
    }

    @Test
    public void testGetPublisher() {
        assertEquals("Klay-Thompson", book.getPublisher());
    }

    @Test
    public void testSetPublisher() {
        book.setPublisher("Pearson");
        assertEquals("Pearson", book.getPublisher());
    }

    @Test
    public void testGetPublishedYear() {
        assertEquals(2018, book.getPublishedYear());
    }

    @Test
    public void testSetPublishedYear() {
        book.setPublishedYear(2006);
        assertEquals(2006, book.getPublishedYear());
    }

    @Test
    public void testGetIsbn() {
        assertEquals("978-0134685991", book.getIsbn());
    }

    @Test
    public void testSetIsbn() {
        book.setIsbn("978-0321349606");
        assertEquals("978-0321349606", book.getIsbn());
    }

    @Test
    public void testGetCopies() {
        assertEquals(5, book.getCopies());
    }

    @Test
    public void testSetCopies() {
        book.setCopies(10);
        assertEquals(10, book.getCopies());
    }
}
