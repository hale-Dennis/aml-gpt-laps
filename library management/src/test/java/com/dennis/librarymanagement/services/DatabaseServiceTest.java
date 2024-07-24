package com.dennis.librarymanagement.services;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseServiceTest {

    @Test
    void testGetConnection() throws SQLException {
        // Mocking the Connection
        Connection connectionMock = Mockito.mock(Connection.class);

        // Mocking DriverManager static method using Mockito
        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarytest", "root", "@Kanekipass12"
            )).thenReturn(connectionMock);

            // Call the method under test
            Connection conn = DatabaseService.getConnection();

            // Verify that the connection is not null
            assertNotNull(conn);

            // Verify that DriverManager.getConnection was called once with the correct parameters
            mockedDriverManager.verify(() -> DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarytest", "root", "@Kanekipass12"
            ));
        }
    }
}
