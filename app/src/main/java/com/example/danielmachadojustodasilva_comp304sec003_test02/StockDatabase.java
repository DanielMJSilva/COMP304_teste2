package com.example.danielmachadojustodasilva_comp304sec003_test02;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {StockInfo.class}, version = 1)
public abstract class StockDatabase extends RoomDatabase {

    public abstract StockDao stockDao();

    private static volatile StockDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StockDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StockDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        StockDatabase.class, "stockDB")

                        .build();
            }
        }
        return INSTANCE;
    }


}

