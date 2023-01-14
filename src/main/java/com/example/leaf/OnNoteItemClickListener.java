package com.example.leaf;

import android.view.View;

public interface OnNoteItemClickListener {
    public void onItemCLick(NoteAdapter.ViewHolder holder, View view,int position);
}
