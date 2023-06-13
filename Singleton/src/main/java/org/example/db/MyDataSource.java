package org.example.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

public class MyDataSource implements IMyDataSource {

    // static fields necessary to create singleton
    private static String urlJdbc;
    private static String user;
    private static String password;
    public static void init(String urlJdbc, String user, String password){
        MyDataSource.urlJdbc = urlJdbc;
        MyDataSource.user = user;
        MyDataSource.password = password;
    }

    // unique reference of the Singleton
    private static volatile MyDataSource myDataSource;

    // composition avec datasource JDBC from a provider
    private DataSource dataSource;

    private MyDataSource(){
        // use urlJdbc, user, password
        if (Objects.isNull(urlJdbc) || Objects.isNull(user) || Objects.isNull(password)) {
            throw new NullPointerException("Missing URL JDBC or user or password");
        }
        // init data source
        String[] jdbcParams = urlJdbc.split(":");
        String provider = jdbcParams[1];
        switch (provider) {
            case "h2":
                this.dataSource = new JdbcDataSource();
                break;;
            case "postgresql":
                this.dataSource = new PGSimpleDataSource();
                break;
            case "mysql":
                this.dataSource = new MysqlDataSource();
                break;
            default:
                throw new IllegalArgumentException("JDBC provider unknown: " + provider);
            // TODO: other providers + exceptions
        }
    }

    public static MyDataSource getInstance() {
        if (myDataSource == null) {
            synchronized (MyDataSource.class) {
                if (myDataSource == null) {
                    myDataSource = new MyDataSource();
                }
            }
        }
        return myDataSource;
    }

    @Override
    public Connection getConnection() {
        return dataSource.getConnection();
    }
}
