package com.iecisa.androidseed.persistence;

import com.iecisa.androidseed.domain.SuperHero;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SuperHero.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract SuperHeroDao heroDao();
}
