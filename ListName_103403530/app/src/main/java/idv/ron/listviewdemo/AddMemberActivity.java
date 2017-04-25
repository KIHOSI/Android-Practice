package idv.ron.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HOSI on 2017/4/16.
 */

public class AddMemberActivity extends AppCompatActivity {
    private Button btBack;
    private Button btAdd;
    private Button btClear;
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
        btClear = (Button) findViewById(R.id.btClear);
        edAddName = (EditText) findViewById(R.id.edAddName);
        edAddAge = (EditText) findViewById(R.id.edAddAge);
        edAddSex = (EditText) findViewById(R.id.edAddSex);
        edAddMajor = (EditText) findViewById(R.id.edAddMajor);

        btClear.setOnClickListener(new View.OnClickListener(){ //清除欄位
            @Override
            public void onClick(View v){
                edAddName.setText("");
                edAddAge.setText("");
                edAddSex.setText("");
                edAddMajor.setText("");
            }
        });

        //確認新增
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                memberList = MyMemberlist.getMemberList();

                boolean isValid =
                        isValid(edAddName,edAddAge,edAddSex,edAddMajor); //驗證
                if (!isValid) {
                    return;
                }


                //獲取值
                String name = edAddName.getText().toString();
                int age = Integer.parseInt(edAddAge.getText().toString());
                String sex = edAddSex.getText().toString();
                String major = edAddMajor.getText().toString();
                Toast.makeText(AddMemberActivity.this,"新增成功", Toast.LENGTH_SHORT).show();
                memberList.add(new Member(name,age,sex,major)); //陣列新增

                //finish(); //返回
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

    private boolean isValid(EditText edName,EditText edAge,EditText edSex,EditText edMajor){ //驗證姓名、年齡、性別、系別
        String name = edName.getText().toString();
        String age = edAge.getText().toString(); //轉成String，為了驗證
        //int age = Integer.parseInt(edAge.getText().toString());
        String sex = edSex.getText().toString();
        String major = edMajor.getText().toString();

        //String pattern1 = "@\"^[\\u4E00-\\u9FA5]{1,4}$\"; "; //只含1到4個漢字
        String pattern1 = "[\\u4e00-\\u9fa5]{1,4}";
        String pattern2 = "[0-9]{1,3}"; //只能輸入3個數字
        //String pattern4 = "@\"^[\\u4E00-\\u9FA5]{1,6}$\"; "; //只含1到6個漢字
        String pattern4 = "[\\u4e00-\\u9fa5]{1,6}";

        if(!name.matches(pattern1)){ //名字不能超過4個漢字
            edName.setError("名字不能超過4個中文字");
            return false;
        }else if(!age.matches(pattern2)){ //年齡不能超過3個正整數
            edAge.setError("年齡不能超過3個正整數");
            Toast.makeText(this, "輸入正整數", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!(sex.matches("男")||sex.matches("女"))){ //性別只能輸入男或女
            edSex.setError("性別只能輸入男|女");
            return false;
        }
        else if(!major.matches(pattern4)){ //系別不能超過6個漢字
            edMajor.setError("系別不能超過6個中文字");
            return false;
        }else{ //以上驗證皆正確
            return true;
        }
    }

}
