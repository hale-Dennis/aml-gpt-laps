package com.dennis.librarymanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student(1, "John Doe", "johndoe@example.com", 5);
    }

    @Test
    public void testGetBooksReserved() {
        assertEquals(5, student.getBooksReserved());
    }

    @Test
    public void testSetBooksReserved() {
        student.setBooksReserved(10);
        assertEquals(10, student.getBooksReserved());
    }

    @Test
    public void testGetId() {
        assertEquals(1, student.getId());
    }

    @Test
    public void testSetId() {
        student.setId(2);
        assertEquals(2, student.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void testSetName() {
        student.setName("Jane Doe");
        assertEquals("Jane Doe", student.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johndoe@example.com", student.getEmail());
    }

    @Test
    public void testSetEmail() {
        student.setEmail("janedoe@example.com");
        assertEquals("janedoe@example.com", student.getEmail());
    }
}
