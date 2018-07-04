package com.example.fahdamin.lab3v2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Button start,pause,stop,restart;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=(ImageView) findViewById(R.id.idImageView);
        textView=(TextView) findViewById(R.id.idTextView);
        String sName=getIntent().getExtras().getString("name");
        final int position=(int) getIntent().getExtras().getInt("position");
        Log.d("Main2Activity","Position: "+position);


        textView.setText(sName);
        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("logo");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bmp);
        imageView.setImageDrawable(ob);



        start=(Button)findViewById(R.id.button1);
        pause=(Button)findViewById(R.id.button2);
        stop=(Button)findViewById(R.id.button3);
        restart=(Button)findViewById(R.id.button4);

        final Integer[] soundsArray={R.raw.bigblood,
                                R.raw.ericcarlson,
        R.raw.hardton,
        R.raw.johnwesley,
        R.raw.jonrose,
        R.raw.manueljgrotesque,
        R.raw.marcoraaphorst,
        R.raw.menstruationsisters,
        R.raw.supercute,
        R.raw.thebooks,
        R.raw.vulvinia,
        R.raw.junior85};

        mp = MediaPlayer.create(this,soundsArray[position]);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mp.isPlaying())
                    mp.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                    mp.pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    mp.seekTo(0);
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mp.pause();
                mp.seekTo(0);
                mp.start();

            }
        });

    }
    @Override
    public void onBackPressed ()
    {
        if (mp != null)
            mp.stop();
        super.onBackPressed();
    }

}
