package idv.ron.listviewdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOSI on 2017/4/11.
 */

public class MyMemberlist {
    public static List<Member> memberList  = new ArrayList();;
    public static int initialFlag=0; //防止按上一步跳回主頁面 會多新增一次

    public static List<Member> getMemberList(){return memberList;}

    public static int getMemberListNum(){return memberList.size();} //得到陣列個數

    public static void createOriginalMembers(){

        if(initialFlag!=1) {
            memberList.add(new Member("張三", 20, "男", "資管"));
            memberList.add(new Member("李四", 22, "男", "資工"));
            memberList.add(new Member("瑪麗", 18, "男", "企管"));
            initialFlag = 1;
        }


    }

}
