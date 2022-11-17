package tables;

import java.sql.SQLException;

public class TableCurator extends TableAbs{

    public TableCurator(String tableName) {
        super("Curator");
    }

    @Override
    public void create() throws SQLException {
        idbExecutor
                .execute("create table Curator (id int NOT NULL AUTO_INCREMENT, FIO varchar(100), PRIMARY KEY (id));",false);
    }

    public void insertDataInCurator(String FIO) throws SQLException {
        idbExecutor.execute(String.format("insert into Curator (FIO) values('%s')",FIO), false);
    }
}
