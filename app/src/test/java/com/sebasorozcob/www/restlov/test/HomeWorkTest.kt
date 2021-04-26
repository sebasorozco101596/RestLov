package com.sebasorozcob.www.restlov.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeWorkTest{

    @Test
    fun `When n is 0 the result is gonna be 0`() {
        val result = HomeWork.fib(0)
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun `When n is 1 the result is gonna be 1`() {
        val result = HomeWork.fib(1)
        assertThat(result).isEqualTo("1")
    }

    @Test
    fun `any n negative number return 0`() {
        val result = HomeWork.fib(-1)
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun `When n is 99 number return 218922995834555169026`() {
        val result = HomeWork.fib(99)
        assertThat(result).isEqualTo("218922995834555169026")
    }

    @Test
    fun `empty string return false`() {
        val result = HomeWork.checkBraces("")
        assertThat(result).isFalse()
    }

    @Test
    fun `string  with () return true`() {
        val result = HomeWork.checkBraces("()")
        assertThat(result).isTrue()
    }

    @Test
    fun `string  with (() return false`() {
        val result = HomeWork.checkBraces("(()")
        assertThat(result).isFalse()
    }

    @Test
    fun `string  with ()) return false`() {
        val result = HomeWork.checkBraces("())")
        assertThat(result).isFalse()
    }

    @Test
    fun `string with )( return false`() {
        val result = HomeWork.checkBraces(")(")
        assertThat(result).isFalse()
    }

}