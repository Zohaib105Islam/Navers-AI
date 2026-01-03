package com.iobits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iobits.data.local.User
import com.iobits.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the user screen.
 * It is annotated with @HiltViewModel to enable dependency injection.
 *
 * @param userRepository The repository for accessing user data.
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * A StateFlow that holds the current list of users from the database.
     * The UI layer can collect this flow to observe data changes in real-time.
     * The flow is started lazily and will be cached for 5 seconds after the last collector is gone.
     */
    val users: StateFlow<List<User>> = userRepository.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    /**
     * Inserts a new user into the database.
     * This operation is performed in a background coroutine launched in the viewModelScope.
     *
     * @param name The name of the user.
     * @param email The email of the user.
     */
    fun insertUser(name: String, email: String) {
        if (name.isBlank() || email.isBlank()) {
            // Optional: Add some validation logic here
            return
        }
        viewModelScope.launch {
            val newUser = User(name = name, email = email)
            userRepository.insertUser(newUser)
        }
    }

    /**
     * Deletes a user from the database.
     * This operation is performed in a background coroutine.
     *
     * @param user The user to delete.
     */
    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }
}
