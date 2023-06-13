package org.example.db;

import java.sql.Connection;

public interface IMyDataSource {
    /**
     * obtain a new connection from this data source
     * @return a new connection
     * @throws MySqlException if it's impossible to get connection
     */
    Connection getConnection();
}
