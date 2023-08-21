package com.suhun.dbaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this, "suhunDB", null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
    }

    public void birthdaySelectFun(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String result = String.format("%s-%s-%s", year, month+1, dayOfMonth);
                birthday.setText(result);
            }
        }, 1977, 00, 01);
        dialog.show();

    }

    public void queryFun(View view){
        execQuery();
    }

    private void execQuery(){
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = db.query("cust", null, null, null, null, null, null);

        while(cursor.moveToNext()){
            String cid = cursor.getString(cursor.getColumnIndexOrThrow("cid"));
            String cname = cursor.getString(cursor.getColumnIndexOrThrow("cname"));
            String ctel = cursor.getString(cursor.getColumnIndexOrThrow("ctel"));
            String cbirthday = cursor.getString(cursor.getColumnIndexOrThrow("cbirthday"));
            String result = String.format("%s:%s:%s:%s\n", cid, cname, ctel, cbirthday);
            stringBuffer.append(result);
            queryResult.setText(stringBuffer);
        }
    }

    public void insertFun(View view){
        ContentValues values = new ContentValues();
        values.put("cname", name.getText().toString());
        values.put("ctel", tel.getText().toString());
        values.put("cbirthday", birthday.getText().toString());
        db.insert("cust", null, values);
        execQuery();
        name.setText("");
        tel.setText("");
        birthday.setText("");
    }

    public void updateFun(View view){
        ContentValues values = new ContentValues();
        if(!idUpdate.getText().toString().equals("")){
            //name
            if(!name.getText().toString().equals("")){
                values.put("cname", name.getText().toString());
            }
            //tel
            if(!tel.getText().toString().equals("")){
                values.put("ctel", tel.getText().toString());
            }
            //birthday
            if(!birthday.getText().toString().equals("Null") || !birthday.getText().toString().equals("")){
                values.put("cbirthday", birthday.getText().toString());
            }
            db.update("cust", values, "cid = ?", new String[]{idUpdate.getText().toString()});
            execQuery();
            name.setText("");
            tel.setText("");
            birthday.setText("");
        }
    }

    public void deleteFun(View view){
        if(!idDelete.getText().toString().equals("")) {
            db.delete("cust", "cid = ?", new String[]{idDelete.getText().toString()});
            execQuery();
            idDelete.setText("");
        }

    }
}