package com.suhun.dbaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView queryResult, birthday;
    private EditText idUpdate, name, tel, idDelete;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSQLiteDataBase();
    }

    private void initView(){
        queryResult = findViewById(R.id.lid_queryResult);
        birthday = findViewById(R.id.lid_birthdayInput);
        idUpdate = findViewById(R.id.lid_whichIdUpdate);
        name = findViewById(R.id.lid_nameInput);
        tel = findViewById(R.id.lid_telInput);
        idDelete = findViewById(R.id.lid_whichIdDelete);
    }

    private void initSQLiteDataBase(){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this, "SuhunDB", null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
    }

    public void birthdaySelectFun(View view){

    }

    public void queryFun(View view){

    }

    public void insertFun(View view){

    }

    public void updateFun(View view){

    }

    public void deleteFun(View view){

    }
}