package com.modnsolutions.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

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

    public void deleteAll() {
        new DeleteAllWordsAsyncTask(mWordDao).execute();
    }

    public void deleteWord(Word word) {
        new DeleteWordAsyncTask(mWordDao).execute(word);
    }

    public void update(Word... words) {
        new UpdateWordAsyncTask(mWordDao).execute(words);
    }


    /**
     * Insert words in background thread.
     */
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

    /**
     * Delete all words in the database on a background thread.
     */
    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mAsyncTaskDao;

        DeleteAllWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * Delete word from database in background.
     */
    private static class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        DeleteWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteWord(words[0]);
            return null;
        }
    }

    /**
     * Update words in the database.
     */
    private static class UpdateWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        UpdateWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.update(words);
            return null;
        }
    }
}
