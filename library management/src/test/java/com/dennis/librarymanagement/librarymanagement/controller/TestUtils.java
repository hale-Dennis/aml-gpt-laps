package com.dennis.librarymanagement.librarymanagement.controller;

import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;

public class TestUtils {
    public static DataSource createInMemoryDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("password");
        return dataSource;
    }
}

