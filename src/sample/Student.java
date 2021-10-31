package sample;

public class Student {
    private int id;
    private String name;
    private String email;
    private int age;
    private  double toan;
    private  double van;
    private  double DTB;


    public Student(int id, String name, String email, int age, double toan, double van,double DTB ){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.toan=toan;
        this.van =van;
        this.DTB=(this.toan+this.van)/2;

    }

    public double getDTB() {
        return DTB;
    }

    public void setDTB(double DTB) {
        this.DTB = DTB;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public double getToan() {
        return toan;
    }

    public void setToan(double toan) {
        this.toan = toan;
    }

    public double getVan() {
        return van;
    }

    public void setVan(double van) {
        this.van = van;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}