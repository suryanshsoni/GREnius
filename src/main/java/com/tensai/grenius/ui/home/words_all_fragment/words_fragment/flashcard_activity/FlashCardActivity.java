package com.tensai.grenius.ui.home.words_all_fragment.words_fragment.flashcard_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;

import java.util.List;

public class FlashCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        List<Word> wordlist = intent.getParcelableArrayListExtra("wordlist");
        setContentView(R.layout.activity_flash_card);

    }
}
