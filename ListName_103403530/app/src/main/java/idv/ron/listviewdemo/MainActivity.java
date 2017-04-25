package idv.ron.listviewdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView tvTitle; //顯示目前陣列有多少資料
    private Context context;
    private List<Member> memberList;
    private ListView lvMember;
    private Button btAdd;
    private Button btDeleteAll;
    private Button btAddExample;
    private Button btShowMale;
    private Button btShowFemale;
    private Button btShowAll;
    private Button btSortByAge;
    private Button btSortByMajor;
    private MemberAdapter memberAdapter;
    private int state; //判斷狀態，ex.排序女(0)、排序男(1)、排序全(2)
    //private int stateSort; //判斷排序狀態，ex.年齡排序(

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lvMember = (ListView) findViewById(R.id.lvMember);
        //ListView lvMember = (ListView) findViewById(R.id.lvMember); //此方法是可以動態變更ArrayList顯示
        btDeleteAll = (Button) findViewById(R.id.btDeleteAll);
        btAdd = (Button) findViewById(R.id.btAdd);
        btAddExample = (Button) findViewById(R.id.btAddExample);
        btShowMale = (Button) findViewById(R.id.btShowMale);
        btShowFemale = (Button) findViewById(R.id.btShowFemale);
        btShowAll = (Button) findViewById(R.id.btShowAll);
        btSortByAge = (Button) findViewById(R.id.btSortByAge);
        btSortByMajor = (Button) findViewById(R.id.btSortByMajor);

        //初始化陣列
        MyMemberlist.createOriginalMembers();

        memberAdapter = new MemberAdapter(this);
        lvMember.setAdapter(memberAdapter);
        //lvMember.setAdapter(new MemberAdapter(this)); //把Adapter放進去

        context = this;

        lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() { //點選資料，跑到細節畫面
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Member member = (Member) parent.getItemAtPosition(position);

                //傳送資料去另一個頁面
                String name = member.getName();
                int age = member.getAge();
                String sex = member.getSex();
                String major = member.getMajor();

                Intent intent = new Intent();
                intent.setClass(context,ResultActivity.class);
                //intent = new Intent(context, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name); //掛載姓名
                bundle.putInt("age",age); //掛載年齡
                bundle.putString("sex",sex); //掛載性別
                bundle.putString("major",major); //掛載系別
                bundle.putInt("position",position+1); //傳送現在位址

                bundle.putSerializable("myList", (Serializable) memberList); //掛載arrayList
                intent.putExtras(bundle);

                startActivity(intent); //傳送
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener(){ //新增畫面
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(context,AddMemberActivity.class);
                startActivity(intent);//傳送頁面
            }
        });

        btDeleteAll.setOnClickListener(new View.OnClickListener(){ //刪除全部資料
            @Override
            public void onClick(View v){
                state = 2; //顯示全部
                MyMemberlist.clearMemberList(); //清空陣列
                memberList = MyMemberlist.getMemberList();
                tvTitle.setText("共"+memberList.size()+"筆資料");
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btAddExample.setOnClickListener(new View.OnClickListener(){ //新增範例
            @Override
            public void onClick(View v){
                MyMemberlist.addMember("張三",20,"男","資管");
                MyMemberlist.addMember("李四",22,"男","資工");
                MyMemberlist.addMember("瑪麗",18,"女","企管");
                MyMemberlist.addMember("甄美麗",19,"女","企管");
                MyMemberlist.addMember("王六",17,"男","資工");
                MyMemberlist.addMember("林志玲",17,"女","資管");
                state = 2; //顯示全部
                memberList = MyMemberlist.getMemberList();
                tvTitle.setText("共"+memberList.size()+"筆資料");
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btSortByAge.setOnClickListener(new View.OnClickListener(){ //依照年齡排序
            @Override
            public void onClick(View v){
                MyMemberlist.sortByAge();
                if(state == 0){ //排序為女
                    memberList = MyMemberlist.getMemberListForFemale();
                }else if(state == 1){ //排序為男
                    memberList = MyMemberlist.getMemberListForMale();
                }else if(state == 2){ //排序為全
                    memberList = MyMemberlist.getMemberList();
                }

                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btSortByMajor.setOnClickListener(new View.OnClickListener(){ //依照系別排序
            @Override
            public void onClick(View v){
                MyMemberlist.sortByMajor();
                if(state == 0){ //排序為女
                    memberList = MyMemberlist.getMemberListForFemale();
                }else if(state == 1){ //排序為男
                    memberList = MyMemberlist.getMemberListForMale();
                }else if(state == 2){ //排序為全
                    memberList = MyMemberlist.getMemberList();
                }
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btShowMale.setOnClickListener(new View.OnClickListener(){ //顯示男生資料
            @Override
            public void onClick(View v){
                memberList = MyMemberlist.getMemberListForMale();
                state = 1;//排序男
                tvTitle.setText("共"+memberList.size()+"筆資料");
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btShowFemale.setOnClickListener(new View.OnClickListener(){ //顯示女生資料
            @Override
            public void onClick(View v){
                memberList = MyMemberlist.getMemberListForFemale();
                state = 0; //排序女
                tvTitle.setText("共"+memberList.size()+"筆資料");
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

        btShowAll.setOnClickListener(new View.OnClickListener(){ //顯示全部
            @Override
            public void onClick(View v){
                memberList = MyMemberlist.getMemberList();
                state = 2; //排序全部
                tvTitle.setText("共"+memberList.size()+"筆資料");
                lvMember.setAdapter(memberAdapter); //重新刷新畫面
            }
        });

    }

    public void onResume(){ //重整頁面時
        super.onResume();
        //List<Member> nowMemberList = memberList; //現在membrList的狀況
        if(state == 0){ //排序為女
            memberList = MyMemberlist.getMemberListForFemale();
        }else if(state == 1){ //排序為男
            memberList = MyMemberlist.getMemberListForMale();
        }else if(state == 2){ //排序為全
            memberList = MyMemberlist.getMemberList();
        }
        tvTitle.setText("共"+memberList.size()+"筆資料");
        //memberAdapter.notifyDataSetChanged(); //當資料內容有變，用此刷新畫面
        lvMember.setAdapter(memberAdapter); //重新刷新畫面，與上面不同點是這個會套用設定的格式
    }

    //此Adpater是增加view(增加顏色)
    private class MemberAdapter extends BaseAdapter { //從BaseAdapter衍生出一個新的class，非匿名inner class
        private LayoutInflater layoutInflater;

        public MemberAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
            memberList = MyMemberlist.getMemberList();
            state = 2; //全部顯示
            //System.out.println("test"); 似乎setAdapter時不會跑進來，只有new時才跑
        }

        @Override
        public int getCount() {
            return memberList.size();
        } //得知有多少count，預留多少位置

        @Override
        public Object getItem(int position) {
            return memberList.get(position);
        }

        @Override
        public long getItemId(int position) { //某種數字?
            return memberList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.listview_item, parent, false); //原本layout什麼都沒有，inflate展開view， inflate就是把xml轉成view
                if (position % 2 == 1)
                    convertView.setBackgroundColor(Color.BLUE);
                if (position % 2 == 0)
                    convertView.setBackgroundColor(Color.YELLOW);
            }


            Member member = memberList.get(position); //得到一個member

            //姓名
            TextView tvName = (TextView) convertView
                    .findViewById(R.id.tvName);
            tvName.setText(member.getName());

            //年齡
            TextView tvAge = (TextView) convertView.findViewById(R.id.tvAge);
            tvAge.setText(String.valueOf(member.getAge()));

            //性別
            TextView tvSex = (TextView) convertView.findViewById(R.id.tvSex);
            tvSex.setText(member.getSex());

            //科系
            TextView tvMajor = (TextView) convertView.findViewById(R.id.tvMajor);
            tvMajor.setText(member.getMajor());

            return convertView;
        }
    }


}
