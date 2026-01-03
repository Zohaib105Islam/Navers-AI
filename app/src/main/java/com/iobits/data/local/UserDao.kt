package com.iobits.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * Inserts a user into the table. If the user already exists, it replaces it.
     * @param user The user object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Deletes a user from the table.
     * @param user The user object to be deleted.
     */
    @Delete
    suspend fun deleteUser(user: User)

    /**
     * Retrieves all users from the table, ordered by their ID in descending order.
     * Returns a Flow, which emits a new list of users whenever the table data changes.
     * @return A Flow emitting a list of User objects.
     */
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<User>>
}