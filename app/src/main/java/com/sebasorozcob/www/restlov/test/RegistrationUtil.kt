package com.sebasorozcob.www.restlov.test

object RegistrationUtil {

    private val existingUsers = listOf("Peter","Carl")

    /**
     * The input is not valid if...
     * ... the username/password is empty
     * ... the username is already taken
     * ... the confirmed password is different to the password
     * ... the password contains less than 2 digits
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false
        }
        if (username in existingUsers) {
            return false
        }
        if (password != confirmPassword) {
            return false
        }
        if (password.count {it.isDigit()} < 2) {
            return false
        }
        return true
    }

}