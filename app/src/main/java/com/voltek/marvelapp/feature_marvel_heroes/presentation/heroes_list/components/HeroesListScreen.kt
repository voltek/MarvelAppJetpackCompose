/*
 * HeroesListScreen.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 21:53
 */

package com.voltek.marvelapp.feature_marvel_heroes.presentation.heroes_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.voltek.marvelapp.R
import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING
import com.voltek.marvelapp.core.util.Constants.SLASH
import com.voltek.marvelapp.core.util.Constants.ZERO
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ResultModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ThumbnailModel
import com.voltek.marvelapp.feature_marvel_heroes.presentation.heroes_list.HeroesListViewModel
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.NavigationConstants
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.PresentationUtils.getImageUrl
import com.voltek.marvelapp.ui.theme.RobotoCondensed

@ExperimentalCoilApi
@Composable
fun HeroesListScreen(
    navController: NavController,
    viewModel: HeroesListViewModel = hiltViewModel()
) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {

        Column {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_marvel_letter_logo),
                contentDescription = "MarvelLetterLogo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(CenterHorizontally)
            )

            SearchBar(
                hint = stringResource(R.string.hint_search_bar),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchCharactersList(it)
            }

            HeroesList(navController = navController)
        }

    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = EMPTY_STRING,
    onSearch: (String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf(EMPTY_STRING)
    }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(26.dp))
                .clip(AbsoluteRoundedCornerShape(20.dp)),
            value = text,
            placeholder = {
                Text(text = hint)
            },
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun HeroNickNameAndName(
    modifier: Modifier = Modifier,
    heroNickName: String,
    comicsQuantity: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        Text(
            text = heroNickName,
            maxLines = 1,
            fontSize = 24.sp,
            fontFamily = RobotoCondensed,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "$comicsQuantity Comics",
            fontSize = 12.sp,
            fontFamily = RobotoCondensed,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@ExperimentalCoilApi
@Composable
fun HeroesList(
    navController: NavController,
    viewModel: HeroesListViewModel = hiltViewModel()
) {
    val heroesList by remember { viewModel.heroesList }
    val endReach by remember { viewModel.endReach }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    LazyColumn {
        val itemCount = heroesList.size

        items(itemCount) {
            HeroEntry(heroesList[it], navController, Modifier)

            if (it >= itemCount - 1 && !endReach && !isLoading && !isSearching) {
                viewModel.loadHeroesPaginated()
            }
        }
    }

    Box(contentAlignment = Center, modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadHeroesPaginated()
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun HeroEntry(
    heroEntry: ResultModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HeroesListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }

    Card(
        shape = RoundedCornerShape(32.dp),
        elevation = 5.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clickable {
                    navController.navigate(getHeroDetailRoute(dominantColor, heroEntry))
                }
        ) {
            Box(modifier = Modifier.weight(1.9f)) {
                val painter = rememberImagePainter(
                    getImageUrl(heroEntry.thumbnailModel),
                    builder = {
                        crossfade(true)
                        //placeholder(R.drawable.ic_marvel_logo)

                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxSize()
                                .scale(0.5f)
                        )
                    }
                )

                (painter.state as? ImagePainter.State.Success)?.let { successState ->
                    LaunchedEffect(Unit) {
                        val drawable = successState.result.drawable

                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    }
                }

                Image(
                    painter = painter,
                    contentDescription = heroEntry.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                dominantColor,
                                defaultDominantColor
                            )
                        )
                    )
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                HeroNickNameAndName(
                    heroNickName = heroEntry.name,
                    comicsQuantity = heroEntry.comicsModel.available
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = heroEntry.description,
                    maxLines = 4,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp),
                    overflow = TextOverflow.Ellipsis,
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "More info",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 2.dp)
                            .align(Alignment.BottomStart),
                    )

                    Icon(
                        painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "Arrow Right",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.BottomEnd),
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry, modifier = Modifier.align(CenterHorizontally)) {
            Text(text = "Retry")
        }
    }

}

private fun getHeroDetailRoute(dominantColor: Color, heroEntry: ResultModel): String {
    return NavigationConstants.HERO_DETAIL_SCREEN +
            "$SLASH${dominantColor.toArgb()}$SLASH${heroEntry.id}"
}


@Preview(showBackground = true)
@Composable
fun HeroNickNameAndName() {
    HeroNickNameAndName(heroNickName = "Wolverine", comicsQuantity = 4)
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(hint = "Search for a Hero")
}

@ExperimentalCoilApi
@Preview
@Composable
fun HeroEntryPreview() {
    HeroEntry(heroEntry = getFakeHeroEntry(), navController = rememberNavController())
}

fun getFakeHeroEntry(): ResultModel {
    return ResultModel(
        description = "Spiderman is a super hero of the Marvel Comics",
        id = ZERO,
        name = "Spiderman",
        thumbnailModel = ThumbnailModel(
            "jpg",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16"
        )
    )
}