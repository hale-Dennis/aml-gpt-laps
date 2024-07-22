package com.dennis.librarymanagement.librarymanagement.services;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DatabaseServiceTest {

    @Test
    public void testGetConnection() throws SQLException {
        // Arrange
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "@Kanekipass12";
        Connection mockConnection = mock(Connection.class);

        // Mock the DriverManager
        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(url, user, password)).thenReturn(mockConnection);

            // Act
            Connection connection = DatabaseService.getConnection();

            // Assert
            assertNotNull(connection);
            mockedDriverManager.verify(() -> DriverManager.getConnection(url, user, password), times(1));
        }
    }
}
