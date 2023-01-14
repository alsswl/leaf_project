package com.example.leaf;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements OnNoteItemClickListener{
    ArrayList<Note> items = new ArrayList<Note>();
    OnNoteItemClickListener listener;
    int layoutType = 0;


    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recylerview_diary,parent,false);

        return new ViewHolder(itemView, this, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note item = items.get(position);
        holder.setItem(item);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Note item){
        items.add(item);
    }

    public void setItems(ArrayList<Note> items){
        this.items = items;
    }

    public Note getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onItemCLick(ViewHolder holder, View view, int position) {
        if(listener!= null)
        {
            listener.onItemCLick(holder,view,position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout1;


        ImageView moodImage;

        TextView day;
        TextView theme;


        public ViewHolder(@NonNull View itemView,final OnNoteItemClickListener listener, int layoutType) {
            super(itemView);
            layout1 = itemView.findViewById(R.id.layout);

            moodImage  = itemView.findViewById(R.id.imageView);

            day = itemView.findViewById(R.id.day);
            theme= itemView.findViewById(R.id.theme);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener !=null){
                        listener.onItemCLick(ViewHolder.this,view,position);
                    }
                }
            });

        }
        public void setItem(Note item){
            String mood = item.getMood();
            int moodIndex = Integer.parseInt(mood);
            setMoodImage(moodIndex);
            day.setText(item.getYear()+"년 "+item.getMonth()+"월 "+item.getDay()+"일");
            theme.setText(item.getTheme());
        }
        public void setMoodImage(int moodIndex){
            switch (moodIndex){
                case 0:
                    moodImage.setImageResource(R.drawable.leaf_sosad);
                    break;
                case 1:
                    moodImage.setImageResource(R.drawable.leaf_sad);
                    break;
                case 2:
                    moodImage.setImageResource(R.drawable.leaf_soso);
                    break;
                case 3:
                    moodImage.setImageResource(R.drawable.leaf_smile);
                    break;
                case 4:
                    moodImage.setImageResource(R.drawable.leaf_happy);
                    break;


            }
        }



    }
}
