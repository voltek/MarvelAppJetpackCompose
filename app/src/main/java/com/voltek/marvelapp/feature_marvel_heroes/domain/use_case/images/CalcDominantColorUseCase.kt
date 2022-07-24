/*
 * CalculateDominantColorUseCase.kt
 * Personal App Android
 * Created by Alan Hernández on 25/02/22 17:35
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.images

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import javax.inject.Inject

class CalcDominantColorUseCase @Inject constructor(){

    operator fun invoke(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate() {
            it?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}