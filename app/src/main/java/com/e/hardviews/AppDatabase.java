package com.e.hardviews;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Action.class}, version = 4)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract MyActionsDBDao actionsDBDao();
    }

