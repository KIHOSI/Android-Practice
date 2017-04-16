package idv.ron.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by HOSI on 2017/4/16.
 */

public class AddMemberActivity extends AppCompatActivity {
    private Button btBack;
    private Button btAdd;
    private List<Member> memberList;
    private  EditText edAddName;
    private  EditText edAddAge;
    private  EditText edAddSex;
    private  EditText edAddMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmember_activity);

        btBack = (Button) findViewById(R.id.btBack);
        btAdd = (Button) findViewById(R.id.btAddConfirm);
        edAddName = (EditText) findViewById(R.id.edAddName);
        edAddAge = (EditText) findViewById(R.id.edAddAge);
        edAddSex = (EditText) findViewById(R.id.edAddSex);
        edAddMajor = (EditText) findViewById(R.id.edAddMajor);

        //確認新增
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                memberList = MyMemberlist.getMemberList();


                //獲取值
                String name = edAddName.getText().toString();
                int age = Integer.parseInt(edAddAge.getText().toString());
                String sex = edAddSex.getText().toString();
                String major = edAddMajor.getText().toString();


                memberList.add(new Member(name,age,sex,major)); //陣列新增

                finish(); //返回
            }
        });

        //回主選單
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
