package org.example.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDataSourceTest {

    @BeforeAll
    static void initSingletonArguments(){
        MyDataSource.init("jdbc:h2:mem:testdb", "john", "doe");
    }

    @BeforeEach
    @AfterEach
    void cleanDataSource() {
        System.out.println("Clean datasource");
        MyDataSource.destroyIfExists();
    }

    // compilation error: ok
//    @Test
//    void testNewImpossible(){
//        var myDataSource = new MyDataSource();
//    }

    @Test
    void testSingletonIsUnique(){
        System.out.println("Use datasource in singleton mode");
        var myDataSource1 = MyDataSource.getInstance();
        var myDataSource2 = MyDataSource.getInstance();
        assertSame(myDataSource1, myDataSource2);
    }

}