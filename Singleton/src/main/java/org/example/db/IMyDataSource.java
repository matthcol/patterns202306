package org.example.db;

import java.sql.Connection;

public interface IMyDataSource {
    Connection getConnection();
}
