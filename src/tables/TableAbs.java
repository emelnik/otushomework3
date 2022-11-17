package tables;

import db.IDBExecutor;
import db.MySQLDBExecuter;

import java.sql.SQLException;

public abstract class TableAbs implements ITable{

    private String tableName;

    protected IDBExecutor idbExecutor;

    public IDBExecutor getIdbExecutor(){
        return idbExecutor;
    }

    public TableAbs(String tableName){
        this.tableName = tableName;
        idbExecutor = new MySQLDBExecuter();
    }

    public void insertDataInTable() {

    }

    @Override
    public void delete() throws SQLException {
        idbExecutor.execute(String.format("drop table %s;", tableName),false);
    }

    protected String createPredicate(String[] predicateValues){
        return String.format("where %s",String.join(" and ",predicateValues));
    }

}
