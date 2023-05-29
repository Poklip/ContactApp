package com.example.contacts.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

val Context.inputMethodManager : InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager