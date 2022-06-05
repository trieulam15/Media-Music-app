package com.example.myapplication;

import java.util.ArrayList;

public class Song extends ArrayList<Song> {
    private final String Title;
    private final int File;

    public Song(String title, int file){
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public int getFile() {
        return File;
    }

}



