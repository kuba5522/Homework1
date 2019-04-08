package com.example.zadanie1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] contact_tab;
    MediaPlayer sound;
    String val1;
    String val2;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        final Button change_contact =  findViewById ( R.id.button );
        final Button change_sound =  findViewById ( R.id.button2 );
        final FloatingActionButton floatingActionButton = findViewById ( R.id.floatingActionButton );
        contact_tab = getResources ().getStringArray ( R.array.kontakty_array );
        final Intent intent1 = getIntent ();
        val1 = intent1.getStringExtra ( "contact_name" );
        if (val1 == null)               //jeśli żaden kontakt nie został przekazany, wybiera domyślnie 1 kontakt
            val1 = contact_tab[0];
        wyswietl(contact_tab, val1);
        soundsy(val2);
        change_contact.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent_contact = new Intent ( getApplicationContext (), Wybor_kontaktu.class );
                startActivityForResult ( intent_contact, SECOND_ACTIVITY_REQUEST_CODE );
            }
        } );
        change_sound.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent_sound = new Intent ( getApplicationContext (), Wybor_dzwieku.class );
                intent_sound.putExtra ( "sound_previous", val2 );
                intent_sound.putExtra ( "contact_name", val1 );
                startActivityForResult ( intent_sound, SECOND_ACTIVITY_REQUEST_CODE );
            }
        } );
        floatingActionButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    if (!sound.isPlaying ()) {
                        sound.start ();
                        sound.setLooping ( true );
                    } else
                        sound.pause ();
                }
                catch (Exception ignored){}
            }
        } );


    }

    public void soundsy(String val2) {
        switch (val2) {
            case "Sound1":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound1 );
                break;
            case "Sound2":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound2 );
                break;
            case "Sound3":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound3 );
                break;
            case "Sound4":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound4 );
                break;
            case "Sound5":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound5 );
                break;
            case "Sound6":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound6 );
                break;
            case "Sound7":
                sound = MediaPlayer.create ( MainActivity.this, R.raw.sound7 );
                break;
        }
    }

    public void wyswietl(String[] contact_tab, String val1) {
        TextView text =  findViewById ( R.id.textView );
        ImageView image =  findViewById ( R.id.imageView );
        for (int i = 0; i < 8; i++)
            if (contact_tab[i].equals ( val1 )) {
                text.setText ( val1 );
                String avatar = "avatar_" + (i + 1);
                int drawable = getResources ().getIdentifier ( avatar, "drawable", getPackageName () );
                image.setImageResource ( drawable );
            }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String val1 = data.getStringExtra ( "contact_name" );
                wyswietl(contact_tab, val1);
                String val2 = data.getStringExtra ( "sound" );
                soundsy(val2);
            }
        }
    }

   @Override
   protected void onStop() {
        try {
            super.onStop ();
            sound.pause ();
        }
        catch (Exception ignored){}
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sound.stop();
    }
}