package dob;

public class Student {

    public Student(String fio, String sex, int id_group){
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    private String fio;
    private String sex;
    private int id_group;

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public int getId_group() {
        return id_group;
    }
}
