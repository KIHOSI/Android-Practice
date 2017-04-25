package idv.ron.listviewdemo;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private TextView tvIndex;
    private Button btFirstData;
    private Button btLastData;
    private List<Member> getMemberList;
    private EditText edName;
    private EditText edAge;
    private EditText edSex;
    private EditText edMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        tvIndex = (TextView) findViewById(R.id.tvIndex);
        btFirstData = (Button) findViewById(R.id.btFirstData);
        btLastData = (Button) findViewById(R.id.btLastData);
        showResults();
    }

    private void showResults() {
        //NumberFormat nf = NumberFormat.getInstance(); //以每三位數一個逗號的方式格式化數字

        Bundle bundle = getIntent().getExtras(); //得到intent傳來的資料
        String name = bundle.getString("name");
        int age = bundle.getInt("age");
        String sex = bundle.getString("sex");
        String major = bundle.getString("major");
        int position = bundle.getInt("position");

        getMemberList = (List<Member>) bundle.getSerializable("myList");
        tvIndex.setText(position+"/"+getMemberList.size());

        //List<Member> memberList = MyMemberlist.getMemberList();
        //tvIndex.setText(position+"/"+memberList.size());

        edName = (EditText) findViewById(R.id.edAddName);
        edAge = (EditText) findViewById(R.id.edAddAge);
        edSex = (EditText) findViewById(R.id.edAddSex);
        edMajor = (EditText) findViewById(R.id.edAddMajor);

        edName.setText(name);
        edAge.setText(String.valueOf(age));
        edSex.setText(sex);
        edMajor.setText(major);

        btFirstData.setOnClickListener(new View.OnClickListener(){ //顯示第一筆資料
            @Override
            public void onClick(View v){
                tvIndex.setText("1/"+getMemberList.size());
                edName.setText(getMemberList.get(0).getName());
                edAge.setText(String.valueOf(getMemberList.get(0).getAge())); //要轉換成string
                edSex.setText(getMemberList.get(0).getSex());
                edMajor.setText(getMemberList.get(0).getMajor());
            }
        });

        btLastData.setOnClickListener(new View.OnClickListener(){ //顯示最後一筆資料
            @Override
            public void onClick(View v){
                int lastIndex = getMemberList.size()-1;
                tvIndex.setText(lastIndex+1 + "/"+getMemberList.size());
                edName.setText(getMemberList.get(lastIndex).getName());
                edAge.setText(String.valueOf(getMemberList.get(lastIndex).getAge())); //
                edSex.setText(getMemberList.get(lastIndex).getSex());
                edMajor.setText(getMemberList.get(lastIndex).getMajor());
            }
        });
    }

    public void onBackClick(View view) {
        finish();
    } //結束自己，就會回到前一個惹
}
