package com.example.hakeem.kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import static com.example.hakeem.kids.R.id.arabic_letter_word_button;
import static com.example.hakeem.kids.R.id.arabic_letters_only_button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button arabicButton = (Button) findViewById(arabic_letter_word_button);
        arabicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent arabicIntent = new Intent(MainActivity.this, ArabicLetterWord.class);
                startActivity(arabicIntent);

            }
        });

        final Button arabicLettersOnly = (Button) findViewById(arabic_letters_only_button);
        arabicLettersOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mathIntent = new Intent(MainActivity.this, ArabicLetterOnly.class);
                startActivity(mathIntent);

            }
        });



    }
}
