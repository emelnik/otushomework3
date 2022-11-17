package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableGroup_Student extends TableAbs{

    public TableGroup_Student(String tableName) {
        super("Group_Student");
    }

    @Override
    public void create() throws SQLException {
        idbExecutor
                .execute("create table Group_Student (id int NOT NULL AUTO_INCREMENT, id_curator int, Name varchar(100), PRIMARY KEY (id));",false);
    }

    public void insertDataInGroupStudent(String id_curator, String Name_Group) throws SQLException {
        idbExecutor.execute(String.format("insert into Group_Student (id_curator, Name) values('%s', '%s')", id_curator, Name_Group), false);
    }

    public void changeCuratorGroup(String idGroup, String curatorId) throws SQLException {
        idbExecutor.execute(String.format("update Group_Student set id_curator = %s where id = %s", curatorId, idGroup), false);
    }

    public void listGroupWithCurator() throws SQLException {
        ResultSet listGroupWithCurator = idbExecutor.execute("Select gs.name,c.FIO from Group_Student  gs INNER JOIN Curator c ON gs.id_curator = c.id",true);
        while (listGroupWithCurator.next()){
            System.out.println("Название группы - "
                    + listGroupWithCurator.getString(1)
                    + " -> ФИО куратора - "
                    + listGroupWithCurator.getString(2));
        }
    }
}
