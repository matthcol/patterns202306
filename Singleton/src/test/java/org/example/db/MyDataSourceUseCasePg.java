package org.example.db;

import org.junit.jupiter.api.BeforeAll;

public class MyDataSourceUseCasePg extends MyDataSourceUseCase {
    @BeforeAll
    static void initSingletonArguments(){
        MyDataSource.init("jdbc:postgresql://localhost:5432/dbcity", "user", "password");
    }
}
