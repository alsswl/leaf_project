package com.example.leaf;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Range;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.channguyen.rsv.RangeSliderView;

import java.util.Calendar;



public class new_diary extends AppCompatActivity {
    TextView textView;
    DatePickerDialog.OnDateSetListener callbackMethod;
    Calendar c = Calendar.getInstance();
    int ny = c.get(Calendar.YEAR);
    int nm = c.get(Calendar.MONTH);
    int nd = c.get(Calendar.DAY_OF_MONTH);
    int moodIndex = 2;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.context = context;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_diary);
        this.InitializeView();
        this.InitializeListener();
        RangeSliderView moodslider = findViewById(R.id.moodbar);
        moodslider.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                moodIndex = index;
            }
        });



}
public void InitializeView(){
        textView = (TextView) findViewById(R.id.date_text);

}
    int year1 = 1;
    int month1 = 1;
    int day1 = 1;
    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView.setText("   "+year + "년" + "  "+(monthOfYear+1) + "월"+"  " + dayOfMonth + "일");
                year1  = year;
                month1 = monthOfYear+1;
                day1 = dayOfMonth;
            }
        };
    }

    public void date(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this,R.style.datepicker ,callbackMethod, ny, nm, nd);

        dialog.show();
    }


    int _id = -1;
    Note item;




    public void save(View view){
        //저장버튼
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        save_note();

    }
    EditText themee = findViewById(R.id.themee);
    EditText contentt = findViewById(R.id.contentt);

    private void save_note(){

        String sql = "insert into" + NoteDatabase.Table_note +
                "(MOOD,THEME,CONTENTS,YEAR,MONTH,DAY) values(" +
                "'"+moodIndex+"'"+
                "'"+themee+"'"+
                "'"+contentt+"'"+
                "'"+year1+"'"+
                "'"+month1+"'"+
                "'"+day1+"')";
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }



}

