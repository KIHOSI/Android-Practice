package idv.ron.listviewdemo;

public class Member { // VO- Value Object
    private int id;
    private String name; //姓名
    private int age; //年齡
    private String sex; //性別
    private String major; //科系
    //private int index; //第幾個位置

    public Member() {
        super();
    }

    public Member(String name,int age,String sex,String major) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.major = major;
    }
    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
