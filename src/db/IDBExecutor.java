package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBExecutor {

    ResultSet execute(String sqlRequest, boolean isResult) throws SQLException;

    void close() throws SQLException;

}
