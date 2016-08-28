package com.example.moresmart_pc006.inner_store_readwrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileRead();


    }
    public void login( View view )
    {
         EditText ed_name;
         EditText ed_passwd;

         CheckBox enterCheck;

         Button enterButton;
        ed_name = (EditText) findViewById(R.id.input_name);
        ed_passwd = (EditText) findViewById(R.id.input_password);

        enterCheck = (CheckBox)findViewById(R.id.enterbox);

        enterButton = (Button)findViewById(R.id.enterButton);
        String name = ed_name.getText().toString();
        String passwd = ed_passwd.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(passwd))
        {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //如果选中自动登入就保存用户名和密码
        if( enterCheck.isChecked() )
        {
            File file = null;
            FileOutputStream fos = null;

            try {
                file = new File("data/data/com.example.moresmart_pc006.inner_store_readwrite/info.txt");
                fos = new FileOutputStream( file );

                fos.write((name+"##"+passwd).getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
    }

    public void fileRead(  )
    {
        EditText ed_name;
        EditText ed_passwd;
        ed_name = (EditText) findViewById(R.id.input_name);
        ed_passwd = (EditText) findViewById(R.id.input_password);

        File file = null;
        FileInputStream fis = null;
        file = new File("data/data/com.example.moresmart_pc006.inner_store_readwrite/info.txt");
        if( !file.exists() )
            return;
        try {

            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
             String text =   br.readLine();
             String[] s =  text.split("##");

            ed_name.setText(s[0]);
            ed_passwd.setText(s[1]);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

