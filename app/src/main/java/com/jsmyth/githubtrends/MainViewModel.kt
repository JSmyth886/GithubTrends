package com.jsmyth.githubtrends

import com.jsmyth.githubtrends.common.ActivityViewModel
import java.util.*

class MainViewModel : ActivityViewModel() {

    private var titles: Stack<String> = Stack()

    fun updateTitle(title: String) {
        titles.push(title)
        setTitle(title)
    }

    fun backPressed() {
        if (titles.size == 1) return
        titles.pop()
        setTitle(titles.peek())
    }
}