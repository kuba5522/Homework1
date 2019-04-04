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
    String avatar;
    MediaPlayer sound;
    String val1;
    String val2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        final Button change_contact =  findViewById ( R.id.button );
        final Button change_sound =  findViewById ( R.id.button2 );
        final TextView text =  findViewById ( R.id.textView );
        final FloatingActionButton floatingActionButton = findViewById ( R.id.floatingActionButton );
        ImageView image =  findViewById ( R.id.imageView );
        contact_tab = getResources ().getStringArray ( R.array.kontakty_array );
        final Intent intent1 = getIntent ();
        val1 = intent1.getStringExtra ( "contact_name" );
        if (val1 == null)               //jeśli żaden kontakt nie został przekazany, wybiera domyślnie 1 kontakt
            val1 = contact_tab[0];
        text.setText ( val1 );
        for (int i = 0; i < 8; i++)                     //wyświetlenie obrazków dla kontaktu, każy kontakt ma swój przypisany obrazek
            if (contact_tab[i].equals ( val1 )) {
                avatar = "avatar_" + (i + 1);
                int drawable = getResources ().getIdentifier ( avatar, "drawable", getPackageName () );
                image.setImageResource ( drawable );
            }
        val2 = intent1.getStringExtra ( "sound" );
        if (val2 == null)           //jeśli żadna muzyka nie została przekazana, wybiera domyślnie 1 muzyke
            val2 = "Sound1";
        //change_sound.setText ( val2 );
        switch (val2) {             //wybór dzwięku
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
        change_contact.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try{                        //to jest po to aby po zmianie aktywności muzyka przestała grać
                    sound.stop ();
                }
                catch (Exception ignored){}
                Intent intent_contact = new Intent ( getApplicationContext (), Wybor_kontaktu.class );
                intent_contact.putExtra ( "contact_previous", text.getText ().toString () );
                startActivity ( intent_contact );
            }
        } );
        change_sound.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String val2 = intent1.getStringExtra ( "sound" );
                if (val2 == null)
                    val2 = "Sound1";
                try{                        //to jest po to aby po zmianie aktywności muzyka przestała grać
                    sound.stop ();
                }
                catch (Exception ignored){}
                Intent intent_sound = new Intent ( getApplicationContext (), Wybor_dzwieku.class );
                intent_sound.putExtra ( "sound_previous", val2 );
                intent_sound.putExtra ( "name", val1 );
                startActivity ( intent_sound );
            }
        } );
        floatingActionButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {   //odtwarzanie dzwięku
                if(!sound.isPlaying ()) {
                    sound.start ();
                    sound.setLooping ( true );
                }
                else
                    sound.pause ();
            }
        } );
    }
}