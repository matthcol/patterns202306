package org.example.db;

import org.junit.jupiter.api.BeforeAll;

public class MyDataSourceUseCaseMariadb extends MyDataSourceUseCase {
    @BeforeAll
    static void initSingletonArguments(){
        MyDataSource.init("jdbc:mariadb://localhost:3306/dbcity", "user", "password");
    }

}
