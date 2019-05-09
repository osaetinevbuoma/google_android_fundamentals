package com.modnsolutions.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.modnsolutions.roomwordssample.REPLY";

    private EditText mEditWordView;
    private Word mUpdateWord;
    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mEditWordView = findViewById(R.id.edit_word);

        // Get intent parcelable from adapter and put in view if it exists.
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("edit_word")) {
            mUpdateWord = intent.getParcelableExtra("edit_word");
            mEditWordView.setText(mUpdateWord.getWord());
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else if (mUpdateWord != null) { // If word for edit is in view
                    // Create new word object with same ID and update
                    // using the view model.
                    Word word = new Word(mUpdateWord.getId(), mEditWordView.getText().toString());
                    mWordViewModel.update(word);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }

                finish();
            }
        });
    }
}
