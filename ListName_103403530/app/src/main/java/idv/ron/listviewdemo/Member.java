package idv.ron.listviewdemo;

import java.io.Serializable;
import java.util.Comparator;

public class Member implements Serializable  { // VO- Value Object
    public int id;
    public String name; //姓名
    public int age; //年齡
    public String sex; //性別
    public String major; //科系

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

   /* public class MemberListComparator extends Comparator<Member> {
        public String compare(Member member1,Member member2){
            return member1.sex.compareTo(member2.sex);
        }
    }*/
}
