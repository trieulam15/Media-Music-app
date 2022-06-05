package com.example.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
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
        mediaPlayer.setOnCompletionListener(mediaPlayer -> btNext.performClick());

        btNext.setOnClickListener(view ->{
            mediaPlayer.stop();
            mediaPlayer.release();
            Post = ((Post+1)%arraySong.size());
            Uri u = Uri.parse(arraySong.get(Post).toString());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);
    });
        btPre.setOnClickListener(view -> {
            mediaPlayer.stop();
            mediaPlayer.release();
            Post = ((Post-1)<0?(arraySong.size()-1):(Post-1));
            Uri u = Uri.parse(arraySong.get(Post).toString());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);

        });
    }
    private void MediaCreate() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(Post).getFile());
        textviewTitle.setText(arraySong.get(Post).getTitle());
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Horsehead Nebula", R.raw.horsehead_nebula));
        arraySong.add(new Song("Stellar Formation", R.raw.stellar_formation));
        arraySong.add(new Song("Vast, Immortal Suns", R.raw.vast_immortal_suns));
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