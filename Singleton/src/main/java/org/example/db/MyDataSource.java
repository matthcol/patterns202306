package org.example.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.mariadb.jdbc.MariaDbDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
            case "h2": {
                var localDataSource = new JdbcDataSource();
                localDataSource.setURL(urlJdbc);
                this.dataSource = localDataSource;
                break;
            }
            case "postgresql": {
                var localDataSource = new PGSimpleDataSource();
                localDataSource.setURL(urlJdbc);
                localDataSource.setUser(user);
                localDataSource.setPassword(password);
                this.dataSource = localDataSource;
                break;
            }
            case "mysql": {
                var localDataSource = new MysqlDataSource();
                localDataSource.setURL(urlJdbc);
                localDataSource.setUser(user);
                localDataSource.setPassword(password);
                this.dataSource = localDataSource;
                break;
            }
            case "mariadb": {
                var localDataSource = new MariaDbDataSource();
                try {
                    localDataSource.setUrl(urlJdbc);
                    localDataSource.setUser(user);
                    localDataSource.setPassword(password);
                    this.dataSource = localDataSource;
                } catch (SQLException e) {
                    throw new MySqlException("Unable to set datasource", e);
                }
                break;
            }
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

    public static void  destroyIfExists() {
        if (myDataSource != null) {
            synchronized (MyDataSource.class) {
                if (myDataSource != null) {
                    myDataSource = null;
                }
            }
        }
    }

    @Override
    public Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new MySqlException("Error while getting connection", e);
        }
    }
}
