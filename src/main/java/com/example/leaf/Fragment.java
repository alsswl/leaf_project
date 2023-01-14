package com.example.leaf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaf.Note;
import com.example.leaf.NoteAdapter;
import com.example.leaf.OnNoteItemClickListener;
import com.example.leaf.OnTabItemSelectedListener;
import com.example.leaf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment extends androidx.fragment.app.Fragment {
    RecyclerView recyclerView;
    NoteAdapter adapter;

    Context context;
    OnTabItemSelectedListener listener;



    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;

        if(context instanceof TabLayout.OnTabSelectedListener){
            listener = (OnTabItemSelectedListener)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment,container,false);
        initUI(rootView);
        loadNoteListData();
        return rootView;


    }

    public void onDetach(){
        super.onDetach();
        if(context!=null){
            context = null;
            listener = null;

        }
    }


    private void initUI(ViewGroup rootView){
      //어댑터 생성(리사이클러뷰에 연결)
        recyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter();
        adapter.addItem(new Note(0,"0","제목","ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ",2022,5,21));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemCLick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
            }
        });



    }

    @SuppressLint("Range")
    public int loadNoteListData(){
        String sql = "select_id, THEME, CONTENTS, MOOD, YEAR, MONTH, DAY " + NoteDatabase.Table_note ;

        int recordCount = -1;
        NoteDatabase database = NoteDatabase.getInstance(context);
        if(database != null){
            //데이터 받음

            Cursor outCursor = database.rawQuery(sql);
             recordCount = outCursor.getCount();
            ArrayList<Note> items = new ArrayList<Note>();
            int i;

            for(i = 0; i< recordCount;i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String mood = outCursor.getString(1);
                String theme = outCursor.getString(2);
                String contents = outCursor.getString(3);
                int year = outCursor.getInt(4);
                int month = outCursor.getInt(5);
                int day = outCursor.getInt(6);

                items.add(new Note(_id,mood,theme,contents,year,month,day));

            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }



}
