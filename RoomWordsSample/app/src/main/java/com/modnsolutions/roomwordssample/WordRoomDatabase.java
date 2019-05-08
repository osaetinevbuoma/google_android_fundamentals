package com.modnsolutions.roomwordssample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Word.class }, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    // Create the WordRoomDatabase as a singleton to prevent having multiple instances of the
    // database opened at the same time, which would be a bad thing.
    private static WordRoomDatabase INSTANCE;
    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // In this practical you don't update the entities and the version
                            // numbers. However, if you modify the database schema, you need to
                            // update the version number and define how to handle migrations. For a
                            // sample app such as the one you're creating, destroying and
                            // re-creating the database is a fine migration strategy. For a real
                            // app, you must implement a non-destructive migration strategy.

                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            // Callback to repopulate database each time app is started.
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * To delete all content and repopulate the database whenever the app is started, you create a
     * RoomDatabase.Callback and override the onOpen() method. Because you cannot do Room database
     * operations on the UI thread, onOpen() creates and executes an AsyncTask to add content to the
     * database.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        public PopulateDBAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database when it is first created.
            mDao.deleteAll();

            for (int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }

            return null;
        }
    }
}
