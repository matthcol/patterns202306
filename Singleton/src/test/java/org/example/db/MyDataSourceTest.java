package org.example.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDataSourceTest {

    @BeforeAll
    static void initSingletonArguments(){
        MyDataSource.init("jdbc:h2:mem:testdb", "john", "doe");
    }

    // compilation error: ok
//    @Test
//    void testNewImpossible(){
//        var myDataSource = new MyDataSource();
//    }

    @Test
    void testSingletonIsUnique(){
        var myDataSource1 = MyDataSource.getInstance();
        var myDataSource2 = MyDataSource.getInstance();
        assertSame(myDataSource1, myDataSource2);
    }

}