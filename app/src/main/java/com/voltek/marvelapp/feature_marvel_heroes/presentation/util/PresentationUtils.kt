/*
 * PresentationUtils.kt
 * Personal App Android
 * Created by Alan Hernández on 27/01/22 0:28
 */

package com.voltek.marvelapp.feature_marvel_heroes.presentation.util

import com.voltek.marvelapp.core.util.Constants.DOT
import com.voltek.marvelapp.core.util.Constants.SLASH
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ThumbnailModel

object PresentationUtils {

    //270x200px
    const val LANDSCAPE_XLARGE_SIZE = "landscape_xlarge"

    //250x156px
    const val LANDSCAPE_AMAZING = "landscape_amazing"

    //464x261px
    const val LANDSCAPE_INCREDIBLE = "landscape_xlarge"

    //168x252px
    const val PORTRAIT_FANTASTIC = "portrait_fantastic"

    //300x450px
    const val PORTRAIT_XLARGE_SIZE = "portrait_xlarge"

    // 216x324px
    const val PORTRAIT_INCREDIBLE = "portrait_incredible"

    //300x450px
    const val PORTRAIT_UNCANNY = "portrait_uncanny"

    //Full image, constrained to 500px wide
    const val DETAIL = "detail"

    const val PAGE_SIZE = 20


    fun getImageUrl(
        thumbnailModel: ThumbnailModel,
        imageSize: String = PORTRAIT_FANTASTIC
    ): String {
        return with(thumbnailModel) { getImageUrl(path, extension, imageSize) }
    }

    private fun getImageUrl(
        path: String,
        imageExtension: String,
        imageSize: String = PORTRAIT_FANTASTIC
    ): String {
        return path + SLASH + imageSize + DOT + imageExtension
    }
}