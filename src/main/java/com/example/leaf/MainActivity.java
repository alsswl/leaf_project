package com.example.leaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;


    String[] years = {"2021","2022","2023","2024","2025","2026","2027"};
    String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String select_year;
    String select_month;
    Calendar now = Calendar.getInstance();
    int now_year = now.get(Calendar.YEAR);
    int now_month = now.get(Calendar.MONTH);
    int i;
    int year_num = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();


       //날짜 검색 스피너
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.spinner_style ,years);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        for(i = 2021;i<=2027;i++){
            if(now_year == i){
                spinner.setSelection(year_num);
            }
            year_num++;

        }




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_year = years[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,R.layout.spinner_style , month);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);
        for(i = 1;i<=12;i++){
            if(now_month == i) {
                spinner2.setSelection(i);

            }

        }


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_month = month[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button button_ok = findViewById(R.id.button3);
        FloatingActionButton write2 = findViewById(R.id.write);


        write2.setOnClickListener(new OnClickListener() {
            //새로쓰기버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),new_diary.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }



}