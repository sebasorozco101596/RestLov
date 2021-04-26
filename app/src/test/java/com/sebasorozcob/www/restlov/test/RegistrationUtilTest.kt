package com.sebasorozcob.www.restlov.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sebas",
            "1234",
            "1234"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sebas",
            "",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty confirmPassword returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sebas",
            "123",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password is not the same as confirmPassword returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sebas",
            "1234",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than two digits returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sebas",
            "abcdef1",
            "abcdef1"
        )
        assertThat(result).isFalse()
    }

}