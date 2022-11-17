package dob;

public class Student {

    public Student(String FIO, String SEX, int id_group){
        this.FIO = FIO;
        this.SEX = SEX;
        this.id_group = id_group;
    }

    private String FIO;
    private String SEX;
    private int id_group;

    public String getFIO() {
        return FIO;
    }

    public String getSEX() {
        return SEX;
    }

    public int getId_group() {
        return id_group;
    }
}
