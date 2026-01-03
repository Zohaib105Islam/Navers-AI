package com.iobits.domain.repository


import com.iobits.data.local.User
import com.iobits.data.local.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository mediates between the ViewModel and the UserDao (the data source).
 *
 * @param userDao The Data Access Object for user operations.
 */
class UserRepository @Inject constructor(private val userDao: UserDao) {

    /**
     * Retrieves a flow of all users from the database.
     * The Flow will automatically emit new values when the underlying data changes.
     * @return A Flow emitting a list of all users.
     */
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    /**
     * Inserts a new user into the database. This is a suspend function,
     * so it must be called from a coroutine.
     * @param user The user to insert.
     */
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    /**
     * Deletes a user from the database. This is a suspend function,
     * so it must be called from a coroutine.
     * @param user The user to delete.
     */
    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}
