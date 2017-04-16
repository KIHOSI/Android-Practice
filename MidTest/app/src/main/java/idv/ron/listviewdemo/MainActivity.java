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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView tvTitle; //顯示目前陣列有多少資料
    private Context context;
    private List<Member> memberList;
    private ListView lvMember;
    private Button btAdd;
    private MemberAdapter memberAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lvMember = (ListView) findViewById(R.id.lvMember);

        //初始化陣列
        MyMemberlist.createOriginalMembers();

        memberAdapter = new MemberAdapter(this);
        lvMember.setAdapter(memberAdapter);
        //lvMember.setAdapter(new MemberAdapter(this)); //把Adapter放進去

        btAdd = (Button) findViewById(R.id.btAdd);
        context = this;

        lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    }

    public void onResume(){ //重整頁面時
        super.onResume();
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
