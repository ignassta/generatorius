package com.example.ignas.roomwordssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NewRecordActivity extends AppCompatActivity {
    public static final String EXTRA_WORD = "com.example.android.roomwordssample.REPLY";
    public static final String EXTRA_TITLE = "com.example.android.roomwordssample.TITLE";

    EditText mEditWordView, mEditTitleView;
    Button buttonSave, buttonGenerate;
    SeekBar seekBar;
    TextView symbolCountView;
    CheckBox checkBoxLetters, checkBoxNumbers, checkBoxMarks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        mEditTitleView = findViewById(R.id.edit_title);
        buttonSave = findViewById(R.id.button_save);
        buttonGenerate = findViewById(R.id.button_generate);
        seekBar = findViewById(R.id.seekBar);
        symbolCountView = findViewById(R.id.symbolCountView);
        checkBoxLetters = findViewById(R.id.checkBoxLetters);
        checkBoxNumbers = findViewById(R.id.checkBoxNumbers);
        checkBoxMarks = findViewById(R.id.checkBoxMarks);

//        slaptažodžio išsaugojimas
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                String title = mEditTitleView.getText().toString();
                replyIntent.putExtra(EXTRA_WORD, word);
                replyIntent.putExtra(EXTRA_TITLE, title);
                setResult(RESULT_OK, replyIntent);
                Toast.makeText(NewRecordActivity.this, "Slaptažodis išsaugotas", Toast.LENGTH_LONG).show();
            }
            finish();
            }
        });

//        slaptažodžio generatoriaus iškvietimas
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEditWordView.setText(stringGenerator(seekBar.getProgress()+4));
                symbolCountView.setText(seekBar.getProgress()+4 + "/12");
                seekBar.setVisibility(View.VISIBLE);
                checkBoxLetters.setVisibility(View.VISIBLE);
                checkBoxNumbers.setVisibility(View.VISIBLE);
                checkBoxMarks.setVisibility(View.VISIBLE);
            }
        });

        checkBoxLetters.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEditWordView.setText(stringGenerator(seekBar.getProgress()+4));
           }
        });

        checkBoxNumbers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEditWordView.setText(stringGenerator(seekBar.getProgress()+4));
            }
        });

        checkBoxMarks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEditWordView.setText(stringGenerator(seekBar.getProgress()+4));
            }
        });


//        slaideris
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                mEditWordView.setText(stringGenerator(progressValue+4));
                symbolCountView.setText(seekBar.getProgress()+4 + "/12");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkbox() {
        mEditWordView.setText(stringGenerator(seekBar.getProgress()+4));
    }

    //    slaptažodžio generatorius
    private String stringGenerator(int length) {
        char[] chars;
        String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_+-:<>?";
        if(checkBoxLetters.isChecked() && checkBoxNumbers.isChecked() && checkBoxMarks.isChecked()) {
            chars = (letters + numbers + symbols).toCharArray();
        } else if(checkBoxLetters.isChecked() && checkBoxNumbers.isChecked()) {
            chars = (letters + numbers).toCharArray();
        } else if(checkBoxLetters.isChecked() && checkBoxMarks.isChecked()) {
            chars = (letters + symbols).toCharArray();
        } else if(checkBoxMarks.isChecked() && checkBoxNumbers.isChecked()) {
            chars = (symbols + numbers).toCharArray();
        } else if(checkBoxLetters.isChecked()) {
            chars = letters.toCharArray();
        } else if(checkBoxNumbers.isChecked()) {
            chars = numbers.toCharArray();
        } else if(checkBoxMarks.isChecked()) {
            chars = symbols.toCharArray();
        } else {
            chars = "x".toCharArray();
        }

        StringBuilder stringbuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            stringbuilder.append(c);
        }
        return stringbuilder.toString();
    }
}
