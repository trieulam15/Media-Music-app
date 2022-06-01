package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textviewTitle, time0, time1;
    SeekBar seekbar1;
    ImageButton btNext, btPre, btPlay;

    ArrayList<Song> arraySong;
    int Post = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();
        AddSong();
        MediaCreate();


        btPlay.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btPlay.setImageResource(R.drawable.ic_action_play_circle_filled);
            } else {
                mediaPlayer.start();
                btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);
            }
        });

        btNext.setOnClickListener(view -> {

        });

        btPre.setOnClickListener(view -> {

        });
    }

    private void MediaCreate() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(Post).getFile());
        textviewTitle.setText(arraySong.get(Post).getTitle());
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Horsehead Nebula", R.raw.Horsehead_Nebula));
        arraySong.add(new Song("Blue Straggler", R.raw.Blue_Straggler));
        arraySong.add(new Song("Stellar Formation", R.raw.Stellar_Formation));
        arraySong.add(new Song("Vast, Immortal Suns", R.raw.Vast_Immortal_Suns));

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