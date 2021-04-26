package com.sebasorozcob.www.restlov.test

import android.util.Log
import java.math.BigDecimal

object HomeWork {

    /**
     *  Returns the n-th fibonacci number
     *  They are defined like this:
     *  fib(0) = 0
     *  fib(1) = 1
     *  fib(n) = fib(n-2) + fib(n-1)
     */
    fun fib(n: Int): String {
        if (n >= 0) {
            if (n == 0 || n == 1) {
                return n.toString()
            }
            var a: BigDecimal = BigDecimal.ZERO
            var b: BigDecimal = BigDecimal.ONE
            var c: BigDecimal = BigDecimal.valueOf(1)
            (1 until n).forEach { _ ->
                c = a + b
                a = b
                b = c
            }
            return c.toString()
        } else {
            return "0"
        }
    }

    /**
     * Checks if the braces are set correctly
     * e.g. "(a * b))" should return false
     */
    fun checkBraces(string: String): Boolean {
        return if (string.isNotEmpty()) {
            val firstAssert: Boolean = string.count { it == '(' } == string.count { it == ')' }
            val newString = string.toCharArray()
            val parenthesis = ArrayList<Char>()
            (newString.indices).forEach { i ->
                if (newString[i] == '(' || newString[i] == ')') parenthesis.add(newString[i])
            }
            val secondAssert = (parenthesis[0] == '(')
            val thirdAssert = (parenthesis[newString.size-1] == ')')
            return firstAssert && secondAssert && thirdAssert
        } else {
            false
        }
    }
}