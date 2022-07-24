/*
 * AutoSizeText.kt
 * Personal App Android
 * Created by Alan Hernández on 25/02/22 21:54
 */

package com.voltek.marvelapp.core.presentation.components.texts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.text.ParagraphIntrinsics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoSizeText(autoSizeTextModel: AutoSizeTextModel) {
    BoxWithConstraints {
        with(autoSizeTextModel) {
            var shrunkFontSize = fontSize
            val calculateIntrinsics = @Composable {
                ParagraphIntrinsics(
                    text, TextStyle(
                        color = color,
                        fontSize = shrunkFontSize,
                        fontWeight = fontWeight,
                        textAlign = textAlign,
                        lineHeight = lineHeight,
                        fontFamily = fontFamily,
                        textDecoration = textDecoration,
                        fontStyle = fontStyle,
                        letterSpacing = letterSpacing
                    ),
                    density = LocalDensity.current,
                    resourceLoader = LocalFontLoader.current
                )
            }

            var intrinsics = calculateIntrinsics()
            with(LocalDensity.current) {
                while (intrinsics.maxIntrinsicWidth > maxWidth.toPx()) {
                    shrunkFontSize *= 0.9
                    intrinsics = calculateIntrinsics()
                }
            }
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = shrunkFontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                onTextLayout = onTextLayout,
                style = style
            )
        }
    }
}

data class AutoSizeTextModel(
    val text: String,
    val modifier: Modifier,
    val color: Color = Color.Black,
    val fontSize: TextUnit = 16.sp,
    val fontStyle: FontStyle = FontStyle.Normal,
    val fontWeight: FontWeight?,
    val fontFamily: FontFamily?,
    val letterSpacing: TextUnit = 0.sp,
    val textDecoration: TextDecoration = TextDecoration.None,
    val textAlign: TextAlign?,
    val lineHeight: TextUnit = 1.sp,
    val onTextLayout: (TextLayoutResult) -> Unit = {},
    val style: TextStyle = TextStyle()
)
