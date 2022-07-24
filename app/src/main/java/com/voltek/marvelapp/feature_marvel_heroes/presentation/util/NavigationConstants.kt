package com.voltek.marvelapp.feature_marvel_heroes.presentation.util

object NavigationConstants {
    const val HEROES_LIST_SCREEN = "hero_list_screen"
    const val HERO_DETAIL_SCREEN = "hero_detail_screen"

    const val DOMINANT_COLOR = "dominantColor"
    const val HERO_ID = "heroId"

    const val HERO_DETAIL_SCREEN_ROUTE = "$HERO_DETAIL_SCREEN/{$DOMINANT_COLOR}/{$HERO_ID}"

}