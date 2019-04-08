package com.example.zadanie1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Wybor_dzwieku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_wybor_dzwieku );
        final RadioGroup radioGroup = findViewById ( R.id.radioGroup );
        final RadioButton[] radioButton = new RadioButton[1];
        final Button button_ok=findViewById ( R.id.button6);
        Button button_cancel=findViewById ( R.id.button5 );
        button_ok.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    int radioId = radioGroup.getCheckedRadioButtonId ();
                    radioButton[0] = findViewById ( radioId );
                    Intent main_intent=new Intent ( );
                    main_intent.putExtra ( "sound", radioButton[0].getText () );
                    setResult ( RESULT_OK, main_intent );
                    finish ();
                    }
                catch (Exception ignored) { }
            }
        } );
        button_cancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent main_intent=new Intent ( );
                setResult ( RESULT_OK, main_intent );
                finish ();
            }
        } );
    }

}
