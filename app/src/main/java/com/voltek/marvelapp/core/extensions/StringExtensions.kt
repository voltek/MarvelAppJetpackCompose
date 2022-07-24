package com.voltek.marvelapp.core.extensions

import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING

/**
 * Returns the value of the optional [String] using a default value when it is null
 * @param default value to return when the string is null
 * @return Value of the string if this is not null; otherwise, return the default value assigned
 */
@JvmOverloads
fun String?.value(default: String = EMPTY_STRING): String = this ?: default