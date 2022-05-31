package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textviewTitle, time0, time1;
    SeekBar seekbar1;
    ImageButton btNext, btPre, btPlay;

    ArrayList<Song> arraySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();
        AddSong();
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song());
    }

    private void Anhxa() {
        textviewTitle = findViewById(R.id.textviewTitle);
        time0 = findViewById(R.id.time0);
        time1 =  findViewById(R.id.time1);
        seekbar1 = findViewById(R.id.seekbar1);
        btNext = findViewById(R.id.btNext);
        btPre = findViewById(R.id.btPre);
        btPlay = findViewById(R.id.btPlay);
    }
}