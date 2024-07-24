package com.dennis.librarymanagement;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Main().start(primaryStage);
        // Save the stage to be used in the test
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;

    @Test
    void testMainStageTitle() {
        // Verify that the stage title is as expected
        assertEquals("Library Management System", primaryStage.getTitle());
    }
}
