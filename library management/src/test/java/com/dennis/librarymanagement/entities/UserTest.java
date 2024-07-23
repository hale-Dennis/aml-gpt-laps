package com.dennis.librarymanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "Dennis", "dennis@example.com") {};
    }

    @Test
    public void testGetId() {

        assertEquals(1, user.getId());
    }

    @Test
    public void testGetName() {

        assertEquals("Dennis", user.getName());
    }



    @Test
    public void testGetEmail() {
        assertEquals(
                "dennis@example.com", user.getEmail());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 3"
    })
    public void testSetId(int initialId, int expectedId) {
        user.setId(initialId);
        assertEquals(expectedId, user.getId());
    }

    @ParameterizedTest
    @CsvSource({
            "Dennis, Dennis",
            "Owusu, Owusu",
            "John, John"
    })
    public void testSetName(String initialName, String expectedName) {
        user.setName(initialName);
        assertEquals(expectedName, user.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "dennis@example.com, dennis@example.com",
            "owusu@example.com, owusu@example.com",
            "john@example.com, john@example.com"
    })
    public void testSetEmail(String initialEmail, String expectedEmail) {
        user.setEmail(initialEmail);
        assertEquals(expectedEmail, user.getEmail());
    }
}
