package org.example.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class MyDataSourceUseCaseH2 extends  MyDataSourceUseCase {
    @BeforeAll
    static void initSingletonArguments(){
        MyDataSource.init("jdbc:h2:mem:testdb", "john", "doe");
    }


}
