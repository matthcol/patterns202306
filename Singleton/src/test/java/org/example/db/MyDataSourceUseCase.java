package org.example.db;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;
import java.text.MessageFormat;

@TestMethodOrder(OrderAnnotation.class)
public abstract class MyDataSourceUseCase {
    private static final String SQL_CREATE_TABLE = """
            create table cities(
                name varchar(100), 
                population int,
                region varchar(100)
            )
    """;

    private static final String SQL_INSERT_DATA = """
            insert into cities (name, population, region) values (?,?,?)
    """;

    private static final String SQL_READ_DATA = """
            select
                name,
                population,
                region
            from cities
            order by name, population desc
    """;

    @Test
    @Order(1)
    void create_table() throws SQLException {
        System.out.println("Create table cities");
        IMyDataSource dataSource = MyDataSource.getInstance();
        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
        ) {
             statement.execute(SQL_CREATE_TABLE);
        } // auto close statement, connection
    }

    @ParameterizedTest
    @CsvSource({
            "Toulouse,471941,Occitanie",
            "Marseille,861635,Provence Alpes Côte d'Azur",
            "Pau,77215,Nouvelle Aquitaine"
    })
    @Order(2)
    void insertData(String name, int population, String region) throws SQLException {
        System.out.println(MessageFormat.format(
                "Insert city: {0}, {1} hab, {2}", name, population, region));
        IMyDataSource dataSource = MyDataSource.getInstance();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DATA);
        ) {
            statement.setString(1, name);
            statement.setInt(2, population);
            statement.setString(3, region);
            statement.execute();
        } // auto close statement, connection
    }

    @Test
    @Order(3)
    void readData() throws SQLException {
        System.out.println("Read cities");
        IMyDataSource dataSource = MyDataSource.getInstance();
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(SQL_READ_DATA);
        ) {
            while (result.next()) {
                System.out.println(MessageFormat.format(
                        "\t- city: {0}, {1} hab, {2}",
                        result.getString("name"),
                        result.getInt("population"),
                        result.getString("region")));
            }
        } // auto close result set, statement, connection
    }

    @BeforeAll
    @AfterAll
    static  void tearDown() {
        System.out.println("Destroy data source");
        MyDataSource.destroyIfExists();;
    }
}
