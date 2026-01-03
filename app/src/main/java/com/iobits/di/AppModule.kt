package com.iobits.di

import android.content.Context
import androidx.room.Room
import com.iobits.data.local.AppDatabase
import com.iobits.data.local.UserDao
import com.iobits.domain.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing database-related dependencies.
 * This module is installed in the SingletonComponent, meaning the provided
 * instances will live as long as the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the AppDatabase.
     * @param context The application context provided by Hilt.
     * @return An instance of AppDatabase.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * Provides a singleton instance of the UserDao.
     * This dependency is created using the AppDatabase instance provided by Hilt.
     * @param appDatabase The AppDatabase instance.
     * @return An instance of UserDao.
     */
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    // Since UserRepository is a class with an @Inject constructor,
    // Hilt already knows how to provide it, so we don't need a @Provides function for it.
}