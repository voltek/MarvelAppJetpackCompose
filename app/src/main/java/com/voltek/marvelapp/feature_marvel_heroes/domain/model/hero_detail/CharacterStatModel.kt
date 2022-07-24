/*
 * CharacterStatModel.kt
 * Personal App Android
 * Created by Alan Hernández on 26/02/22 0:46
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.model.hero_detail

import com.voltek.marvelapp.core.util.Constants.ZERO

data class CharacterStatModel(
    val iconResId: Int = ZERO,
    val statTitleResId: Int = ZERO,
    val statValue: Int = ZERO,
)