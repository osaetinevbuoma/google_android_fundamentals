package com.modnsolutions.roomwordssample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
