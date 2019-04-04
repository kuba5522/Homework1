package com.example.zadanie1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Wybor_kontaktu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_wybor_kontaktu );
        Spinner spinner = (findViewById ( R.id.spinner ));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kontakty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener ( this );
    }

    @Override
    public void onItemSelected(final AdapterView <?> parent, View view, final int position, long id) {
        final Button button_ok= findViewById ( R.id.button3 ) ;
        final Button button_cancel= findViewById ( R.id.button4 ) ;
        button_ok.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String text=parent.getItemAtPosition ( position ).toString ();
                //Toast.makeText ( parent.getContext (),text,Toast.LENGTH_SHORT ).show ();
                Intent main_intent=new Intent ( getApplicationContext (), MainActivity.class);
                main_intent.putExtra ( "contact_name", text);
                startActivity ( main_intent );
            }

        } );
        button_cancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Toast.makeText ( parent.getContext (),text,Toast.LENGTH_SHORT ).show ();
                Intent main_intent=new Intent ( getApplicationContext (), MainActivity.class);
                Intent intent1=getIntent ();
                String contact_previous = intent1.getStringExtra ("contact_previous");
                main_intent.putExtra ( "contact_name", contact_previous);
                startActivity ( main_intent);
        }
        } );
    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }
}
