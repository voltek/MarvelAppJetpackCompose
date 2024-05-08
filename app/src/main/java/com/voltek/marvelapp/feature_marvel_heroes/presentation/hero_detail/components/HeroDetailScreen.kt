/*
 * HeroDetailScreen.kt
 * Personal App Android
 * Created by Alan Hernández on 23/02/22 22:38
 */

package com.voltek.marvelapp.feature_marvel_heroes.presentation.hero_detail.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.voltek.marvelapp.R
import com.voltek.marvelapp.core.presentation.components.texts.AutoSizeText
import com.voltek.marvelapp.core.presentation.components.texts.AutoSizeTextModel
import com.voltek.marvelapp.core.util.Constants
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ComicsModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.EventsModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ResultModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.SeriesModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.StoriesModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ThumbnailModel
import com.voltek.marvelapp.feature_marvel_heroes.presentation.hero_detail.HeroDetailViewModel
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.PresentationUtils.LANDSCAPE_AMAZING
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.PresentationUtils.getImageUrl
import com.voltek.marvelapp.ui.theme.RobotoCondensed
import com.voltek.marvelapp.ui.theme.StatBackground
import com.voltek.marvelapp.ui.theme.StatIconColor

@Composable
fun HeroDetailScreen(
    navController: NavController,
    characterId: String,
    dominantColor: Color,
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    rememberSystemUiController().setSystemBarsColor(color = dominantColor)

    val characterDetail by remember { viewModel.heroEntry }

    viewModel.getCharacterDetail(characterId)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeroDetailTopSection(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            navController = navController,
            heroEntry = characterDetail,
        )
        HeroDetailStateWrapper(
            characterEntry = characterDetail,
            viewModel = viewModel
        )
    }
}

@Composable
fun HeroDetailTopSection(
    navController: NavController,
    heroEntry: ResultModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = rememberImagePainter(
                getImageUrl(heroEntry.thumbnailModel, LANDSCAPE_AMAZING),
                builder = {
                    crossfade(true)
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(0.5f)
                    )
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.cd_character_image),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .alpha(0.1f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black,
                            Color.Transparent
                        )
                    )
                )
        ) {

        }

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.cd_back_arrow),
            tint = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 8.dp, top = 8.dp)
                .clickable { navController.popBackStack() }
        )
    }
}

@Composable
fun HeroDetailStateWrapper(
    modifier: Modifier = Modifier,
    characterEntry: ResultModel,
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxSize()
            .offset(y = (-20).dp)
            .padding(bottom = 16.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            AutoSizeText(
                AutoSizeTextModel(
                    text = characterEntry.name,
                    fontSize = 24.sp,
                    fontFamily = RobotoCondensed,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = characterEntry.description,
                fontSize = 14.sp,
                fontFamily = RobotoCondensed,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CharacterDetailStatsSection(characterEntry = characterEntry, viewModel = viewModel)
        }
    }
}

@Composable
fun CharacterDetailStatsSection(
    modifier: Modifier = Modifier,
    characterEntry: ResultModel,
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        Divider(color = Color.LightGray, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val characterStats = viewModel.getCharacterStatsList(characterEntry)

            characterStats.maxOf { it.statValue }

            items(characterStats) {
                CharacterStatElement(
                    iconResId = it.iconResId,
                    statTitle = stringResource(it.statTitleResId),
                    statValue = it.statValue
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    }
}

@Composable
fun CharacterStatElement(
    modifier: Modifier = Modifier,
    iconResId: Int,
    statTitle: String,
    statValue: Int = 0
) {

    val stat by remember { mutableStateOf(statValue) }

    val statCounter by animateIntAsState(
        targetValue = statValue,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(40.dp))
                .background(StatBackground)
                .clickable { }
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = stringResource(id = R.string.cd_character_stat),
                tint = StatIconColor,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 10.dp)
            )
        }

        Text(
            text = statTitle,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = StatIconColor,
            fontFamily = RobotoCondensed,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = statCounter.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = RobotoCondensed,
            fontWeight = FontWeight.Bold
        )
    }
}

/* @Preview
@Composable
fun HeroDetailTopSectionPreview() {
    HeroDetailTopSection(getFakeHeroEntry())
}
 */

@Preview(showBackground = true)
@Composable
fun CharacterStatElementPreview() {
    CharacterStatElement(iconResId = R.drawable.ic_comic, statTitle = "Comics")
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailStatsSectionPreview() {
    CharacterDetailStatsSection(characterEntry = getFakeHeroEntry())
}

fun getFakeHeroEntry(): ResultModel {
    return ResultModel(
        description = "Spiderman is a super hero of the Marvel Comics",
        id = Constants.ZERO,
        name = "Spiderman",
        thumbnailModel = ThumbnailModel(
            "jpg",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16"
        ),
        comicsModel = ComicsModel(10),
        seriesModel = SeriesModel(15),
        storiesModel = StoriesModel(20),
        eventsModel = EventsModel(25),
    )
}