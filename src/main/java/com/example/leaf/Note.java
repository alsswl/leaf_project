package com.example.leaf;

public class Note {
    int _id;
    String mood;
    String theme;
    String contents;
    int year;
    int month;
    int day;



    public Note(int _id, String mood, String theme, String contents, int year,int month,int day){
        this._id = _id;
        this.mood = mood;
        this.theme = theme;
        this.contents = contents;
        this.year = year;
        this.month = month;
        this.day = day;

    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


}
