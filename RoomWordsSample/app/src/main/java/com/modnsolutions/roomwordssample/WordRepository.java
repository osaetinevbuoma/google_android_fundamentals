package com.modnsolutions.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /**
     * Add a wrapper for the insert() method. Use an AsyncTask to call insert() on a non-UI thread,
     * or your app will crash. Room ensures that you don't do any long-running operations on the
     * main thread, which would block the UI.
     *
     * @param word The word to insert into the database.
     */
    public void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }


    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
