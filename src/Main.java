import db.IDBExecutor;
import db.MySQLDBExecuter;
import tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {

        ArrayList<String> list = new ArrayList<>();

        IDBExecutor idbExecutor = new MySQLDBExecuter();

        TableStudent tableStudent = new TableStudent("Student");
        TableCurator tableCurator = new TableCurator("Curator");
        TableGroup_Student tableGroupStudent = new TableGroup_Student("Group_Student");

        try {

        ResultSet tables = idbExecutor.execute("show tables;",true);

            while (tables.next()){
            String columnName = tables.getString(1);
            list.add(columnName);
            }

            for (String list2:list) {
                if(list2.equals("Student")){
                    tableStudent.delete();
                }else if(list2.equals("Curator")){
                    tableCurator.delete();
                }else if(list2.equals("Group_Student")){
                    tableGroupStudent.delete();
                }
            }

        tableStudent.create();
        tableStudent.insertDataInStudent("1","Petrov Ivan Petrovich","male");
        tableStudent.insertDataInStudent("1","Kostin Oleg Petrovich","male");
        tableStudent.insertDataInStudent("3","Petrova Alla Petrovna","female");
        tableStudent.insertDataInStudent("2","Pushkin Alex Nikolaevich","male");
        tableStudent.insertDataInStudent("1","Krylova Nata Igorevna","female");
        tableStudent.insertDataInStudent("1","Dugina Olga Viktorovna","female");
        tableStudent.insertDataInStudent("2","Dutin Vladimir Nikolaevich","male");
        tableStudent.insertDataInStudent("3","Shushkin Ignat Olegich","male");
        tableStudent.insertDataInStudent("2","Katc Mikhail Semenovich","male");
        tableStudent.insertDataInStudent("1","Shtok Oleg Ivanich","male");
        tableStudent.insertDataInStudent("1","Art Yann Nikolaevich","male");
        tableStudent.insertDataInStudent("2","Savkina Anna Petrovna","female");
        tableStudent.insertDataInStudent("1","Kozlova Elena Petrovna","female");
        tableStudent.insertDataInStudent("2","Nikitina Liza Petrovna","female");

        tableCurator.create();
        tableCurator.insertDataInCurator("Груздь Иван Петрович");
        tableCurator.insertDataInCurator("Попкова Алла Ивановна");
        tableCurator.insertDataInCurator("Грачев Ильдар Назимович");
        tableCurator.insertDataInCurator("Козина Алла Петровна");

        tableGroupStudent.create();
        tableGroupStudent.insertDataInGroupStudent("1", "Лучшая группа на земле");
        tableGroupStudent.insertDataInGroupStudent("2", "Худшая группа на земле");
        tableGroupStudent.insertDataInGroupStudent("3", "Самые отморозки");

        //Вывести на экран информацию о всех студентах включая название группы и имя куратора
            tableStudent.listAllStudentWithCurator();
            System.out.println("");

        //Вывести на экран количество студентов
            tableStudent.countStudents();
            System.out.println("");

        //Вывести студенток
            tableStudent.listWomanStudent();
            System.out.println("");

        //Обновить данные по группе сменив куратора
            tableGroupStudent.changeCuratorGroup("3","2");

        //Вывести список групп с их кураторами
            tableGroupStudent.listGroupWithCurator();
            System.out.println("");

        //Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)
            tableStudent.listStudentsFromSpecificGroup("Лучшая группа на земле");
            System.out.println("");

            }finally {
            idbExecutor.close();
        }
    }
}
