/*
 * DispatcherProvider.kt
 * Personal App Android
 * Created by Alan Hernández on 12/03/22 2:22
 */

package com.voltek.marvelapp.core.presentation.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}