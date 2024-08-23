package com.example.s1723;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,prn;
    Button insert,select;
    TextView tv;
//    ListView listofsql;
//    ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText1);
        prn = findViewById(R.id.editText2);
        insert = findViewById(R.id.button1);
        select = findViewById(R.id.button2);
        tv = findViewById(R.id.textView8);
//        listofsql = findViewById(R.id.listView1);
//        listItem = new ArrayList<>();


        DatabaseHandler dbh = new DatabaseHandler(MainActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameofstudent = name.getText().toString();
                String prnofstudent = prn.getText().toString();
                dbh.opendb();
                dbh.insertvalue(prnofstudent,nameofstudent);

                Toast.makeText(MainActivity.this, "data insertedd", Toast.LENGTH_SHORT).show();
            }
        });// end of insert

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.opendb();
                Cursor cursor = dbh.selectdata();
                StringBuilder data = new StringBuilder();
                if (cursor != null && cursor.moveToFirst()) {
                    data.append("Database: ").append(dbh.getDatabaseName()).append("\n");
                    data.append("Table: ").append(DatabaseHandler.tablename).append("\n");
                    do {
                        @SuppressLint("Range") String prn = cursor.getString(cursor.getColumnIndex(DatabaseHandler.prncol));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHandler.namecol));
                        data.append("PRN: ").append(prn).append(", Name: ").append(name).append("\n");
                    } while (cursor.moveToNext());
                    cursor.close();
                }
                dbh.closedb();
                tv.setText(data.toString()); // Set the text to TextView

            }
        });


    }

}