package idv.ron.listviewdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HOSI on 2017/4/11.
 */

public class MyMemberlist { //implements Serializable
    public static List<Member> memberList  = new ArrayList();
    public static int initialFlag=0; //防止按上一步跳回主頁面 會多新增一次


    public static void createOriginalMembers(){

        if(initialFlag!=1) {
            memberList.add(new Member("張三", 20, "男", "資管"));
            memberList.add(new Member("李四", 22, "男", "資工"));
            memberList.add(new Member("瑪麗", 18, "女", "企管"));
            initialFlag = 1;
        }
    }

    public static List<Member> getMemberList(){return memberList;} //得到全部arraylist

    public static List<Member> getMemberListForMale(){ //只得到男生的資料
        List<Member> memberListForMale = new ArrayList<>();
        for(int i = 0 ; i < memberList.size() ; i++){
            if(memberList.get(i).getSex().matches("男")){ //如果性別為"男"
                memberListForMale.add(memberList.get(i));
            }
        }
        return memberListForMale;
    }

    public static List<Member> getMemberListForFemale(){ //只得到女生的資料
        List<Member> memberListForFemale = new ArrayList<>();
        for (int i = 0 ; i < memberList.size() ; i++){
            if(memberList.get(i).getSex().matches("女")){ //如果性別為女生
                memberListForFemale.add(memberList.get(i));
            }
        }
        return memberListForFemale;
    }

    public static void clearMemberList(){
        memberList.clear(); //清空陣列
    }

    public static void sortByAge(){ //依年齡排序
        Collections.sort(memberList, new Comparator<Member>() {
            @Override
            public int compare(Member m1, Member m2) {
                return m1.age - m2.age;
            }
        });
    }

    public static void sortByMajor(){ //依系別排序
        Collections.sort(memberList,new Comparator<Member>(){
            @Override
            public int compare(Member m1,Member m2){
                return m1.major.compareTo(m2.major);
            }
        });
    }

    public static void addMember(String name,int age,String sex,String major){ //新增會員
        memberList.add(new Member(name,age,sex,major));
    }

}
