package org.example.db;

public class MySqlException extends RuntimeException {
    public MySqlException() {
    }

    public MySqlException(String message) {
        super(message);
    }

    public MySqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public MySqlException(Throwable cause) {
        super(cause);
    }

    public MySqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
