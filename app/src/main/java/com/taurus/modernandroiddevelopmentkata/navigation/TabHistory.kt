package com.taurus.modernandroiddevelopmentkata.navigation

import java.io.Serializable
import java.util.*

class TabHistory(initialValue: Int) : Serializable {

    private val stack: Deque<Int> = ArrayDeque()

    init {
        stack.push(initialValue)
    }

    fun push(entry: Int) {
        stack.push(entry)
    }

    fun popPrevious(): Int? = stack.run {
        if (canGoBack()) {
            pop()
            peek()
        } else {
            null
        }
    }

    fun peek() = stack.peek()

    private fun canGoBack() = stack.size > 1
}

