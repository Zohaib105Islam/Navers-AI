package com.iobits.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The main database class for the application.
 *
 * This class is annotated with @Database, listing all the entities and the database version.
 * It provides an abstract method to get the DAO for each entity.
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides the Data Access Object (DAO) for the User entity.
     * @return An instance of UserDao.
     */
    abstract fun userDao(): UserDao
}
