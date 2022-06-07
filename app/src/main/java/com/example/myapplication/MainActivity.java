package com.example.myapplication;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
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
            setTimeTotal();
            UpdateTimeSong();
        });
        mediaPlayer.setOnCompletionListener(mediaPlayer -> btNext.performClick());

        btNext.setOnClickListener(view ->{
            Post++;
            if (Post > arraySong.size() - 1)
            {
                Post = 0;
            }
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            MediaCreate();
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);
            setTimeTotal();
            UpdateTimeSong();
    });
        btPre.setOnClickListener(view -> {
            Post--;
            if (Post < 0)
            {
                Post = arraySong.size() - 1;
            }
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            MediaCreate();
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);
            setTimeTotal();
            UpdateTimeSong();
        });
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekbar1.getProgress());
            }
        });
    }

    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
                time0.setText(dinhdangGio.format(mediaPlayer.getCurrentPosition()));
                seekbar1.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(mediaPlayerP -> {
                    Post++;
                    if (Post > arraySong.size() - 1)
                    {
                        Post = 0;
                    }
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                    MediaCreate();
                    mediaPlayer.start();
                    btPlay.setImageResource(R.drawable.ic_action_pause_circle_outline);
                    setTimeTotal();
                    UpdateTimeSong();
                });
                handler.postDelayed(this, 500);

            }
        }, 100);
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
    private void setTimeTotal(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dinhdang = new SimpleDateFormat("mm:ss");
        time1.setText(dinhdang.format(mediaPlayer.getDuration()));
        seekbar1.setMax(mediaPlayer.getDuration());
    }
}