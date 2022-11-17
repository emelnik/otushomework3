package db;

import conf_readers.IConfReader;
import conf_readers.PropertiesReader;

import java.sql.*;
import java.util.Map;

public class MySQLDBExecuter implements IDBExecutor{

    private IConfReader confReader = new PropertiesReader();
    private static Connection connection = null;
    private static Statement statement = null;

    @Override
    public ResultSet execute(String sqlRequest, boolean isResult) throws SQLException{

        Map<String, String> confData = confReader.read();

            if(connection == null) {
                connection = DriverManager.getConnection(
                        String.format("%s/%s", confData.get("url"), confData.get("db_name")),
                        confData.get("username"),
                        confData.get("password"));
                statement = connection.createStatement();
            }

            if(isResult){
                return statement.executeQuery(sqlRequest);
            }

            statement.execute(sqlRequest);

            return null;

    }

    @Override
    public void close() throws SQLException {
        if(statement != null){
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
