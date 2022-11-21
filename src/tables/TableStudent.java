package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableStudent extends TableAbs implements ITable{

    public TableStudent(String tableName) {
        super("Student");
    }

    @Override
    public void create() throws SQLException {
        idbExecutor
                .execute("create table Student (id int NOT NULL AUTO_INCREMENT, id_group int, FIO varchar(100), SEX varchar(10), PRIMARY KEY (id));",false);
    }

    public void insertDataInStudent(String id_group, String fio, String sex) throws SQLException {
        idbExecutor.execute(String.format("insert into Student (id_group, FIO, SEX) values(%s, '%s', '%s')", id_group, fio, sex), false);
    }

    public void countStudents() throws SQLException {
        ResultSet count = idbExecutor.execute("select COUNT(*) from Student",true);
        while (count.next()){
            System.out.println("Количество студентов - " + count.getString(1));
        }
    }

    public void listWomanStudent() throws SQLException {
        ResultSet woman = idbExecutor.execute("select FIO from Student where sex = 'female'",true);
        while (woman.next()){
            System.out.println("Студентки - " + woman.getString(1));
        }
    }

    public void listAllStudentWithCurator() throws SQLException {
        ResultSet listAllStudentWithCurator = idbExecutor.execute("select s.id, s.FIO, s.SEX, gs.Name, c.FIO from student s inner join group_student gs ON s.id_group = gs.id inner join curator c ON gs.id_curator = c.id;",true);
        while (listAllStudentWithCurator.next()){
            System.out.println("Номер студента - " + listAllStudentWithCurator.getString(1)
                    + " ФИО студента - "
                    + listAllStudentWithCurator.getString(2)
                    + " Пол студента - "
                    + listAllStudentWithCurator.getString(3)
                    + " Название группы - "
                    + listAllStudentWithCurator.getString(4)
                    + " ФИО куратора группы - "
                    + listAllStudentWithCurator.getString(5));
        }
    }

    public void listStudentsFromSpecificGroup(String name) throws SQLException {
        ResultSet listStudentsFromSpecificGroup = idbExecutor.execute(String.format("select FIO, SEX from student s where id_group in (SELECT id from Group_Student gs where gs.Name ='%s');",name),true);
        System.out.println(String.format("В выбранной группе \"%s\" учатся", name));
        while (listStudentsFromSpecificGroup.next()){
            System.out.println("Студент(ка) - "
                    + listStudentsFromSpecificGroup.getString(1)
                    + " -> Пол студента - "
                    + listStudentsFromSpecificGroup.getString(2));
        }
    }
}
