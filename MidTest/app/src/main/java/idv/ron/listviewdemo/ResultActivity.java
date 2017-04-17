package idv.ron.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    //private TextView tvResult;
    private TextView tvIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        //tvResult = (TextView) findViewById(R.id.tvResult);
        tvIndex = (TextView) findViewById(R.id.tvIndex);
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
        List<Member> memberList = MyMemberlist.getMemberList();
        tvIndex.setText(position+"/"+memberList.size());

        EditText edName = (EditText) findViewById(R.id.edAddName);
        EditText edAge = (EditText) findViewById(R.id.edAddAge);
        EditText edSex = (EditText) findViewById(R.id.edAddSex);
        EditText edMajor = (EditText) findViewById(R.id.edAddMajor);

        edName.setText(name);
        edAge.setText(String.valueOf(age));
        edSex.setText(sex);
        edMajor.setText(major);
    }

    public void onBackClick(View view) {
        finish();
    } //結束自己，就會回到前一個惹
}
