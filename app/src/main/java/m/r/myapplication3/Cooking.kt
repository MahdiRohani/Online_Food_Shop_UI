package m.r.myapplication3

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import org.w3c.dom.Text
import kotlin.math.max
import kotlin.math.min


val AppBarCollapse = 56.dp
val AppBarExpended = 400.dp

@Composable
fun Cooking() {
    val scrollState = rememberLazyListState()
    Content(scrollState = scrollState)
    ToolBAr(scrollState = scrollState)

}


@Composable
fun Content(scrollState: LazyListState) {
    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpended),
        state = scrollState
    ) {
        item {
            BasicInfo()
            Description()
            Serving()
            Shopping()
            SimilarFoodHeader()
            SimilarFood()
            SimilarRecipeHeader()
            SimilarRecipes()
        }
    }
}

@Composable
fun BasicInfo() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(image = R.drawable.clock, text = "60min")
        InfoColumn(image = R.drawable.fire, text = "357 Kcal")
        InfoColumn(image = R.drawable.star, text = "4")

    }
}

@Composable
fun Description() {
    Text(
        text = "very good",
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun InfoColumn(@DrawableRes image: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = image), contentDescription = "",
            tint = Color(0xFFE91E8E),
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Serving() {
    var value by remember {
        mutableStateOf(0)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(shape = RoundedCornerShape(2.dp))
            .background(Color(0xFFE9E5E5))
    ) {
        Text(
            text = "Serving",
            Modifier.weight(1f),
            fontWeight = FontWeight.Medium
        )
        CircularButton(iconRes = Icons.Filled.Add, elevation = null, color = Color(0xFFE91E8E)) {
            value++
        }
        Text(text = "$value", Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
        CircularButton(iconRes = Icons.Filled.Clear, elevation = null, color = Color(0xFFE91E8E)) {
            if (value > 0) {
                value--
            }
        }
    }
}


@Composable
fun ToolBAr(scrollState: LazyListState) {
    val imageHeight = AppBarExpended - AppBarCollapse
    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.statusBars.layoutInsets.top
    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)
    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset
    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color.White,
        modifier = Modifier
            .height(AppBarExpended)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(imageHeight)
                .graphicsLayer {
                    alpha = 1f - offsetProgress
                }) {
                Image(
                    painter = painterResource(id = R.drawable.food1), contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Color.Transparent),
                                    Pair(1f, Color.White)
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Desert",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(vertical = 6.dp, horizontal = 6.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFE9E5E5))
                    )

                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapse),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Cake",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }

    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapse)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(iconRes = Icons.Filled.ArrowBack)
        CircularButton(iconRes = Icons.Filled.FavoriteBorder)
    }


}

@Composable
fun Shopping() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFE91E8E),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add to shopping list",
                Modifier.padding(16.dp)
            )

        }
    }
}

@Composable
fun CircularButton(
    @SuppressLint("SupportAnnotationUsage") @DrawableRes iconRes: ImageVector,
    color: Color = Color.Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onclick: () -> Unit = {}
) {
    Button(
        onClick = onclick,
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
            .padding(end = 8.dp)
    ) {
        Icon(iconRes, contentDescription = "")
    }
}

@Composable
fun SimilarFoodHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Similar foods", fontWeight = FontWeight.Bold)
            Text(text = "You may like these...", color = Color.DarkGray)
        }
        Button(
            onClick = { /*TODO*/ },
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color(0xFFE91E8E)
            )
        ) {
            Text(text = "Show more")
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Composable
fun SimilarFood() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FastFoodItem(
            img = painterResource(id = R.drawable.hamburger),
            name = "hamburger",
            desc = "Fast food",
            price = 100
        )
        FastFoodItem(
            img = painterResource(id = R.drawable.hamburger),
            name = "hamburger",
            desc = "Fast food",
            price = 100
        )
        FastFoodItem(
            img = painterResource(id = R.drawable.hamburger),
            name = "hamburger",
            desc = "Fast food",
            price = 100
        )
        FastFoodItem(
            img = painterResource(id = R.drawable.hamburger),
            name = "hamburger",
            desc = "Fast food",
            price = 100
        )

    }
}

@Composable
fun FastFoodItem(
    img: Painter,
    name: String,
    desc: String,
    price: Int
) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .height(250.dp)
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE9E5E5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 55.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = desc,
                    modifier = Modifier.padding(top = 5.dp),
                    color = Color.Gray
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$$price",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color(0xFFE91E8E)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxHeight()
                            .width(38.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFE91E8E),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "")
                    }
                }

            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = img, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
        }
    }

}

@Composable
fun SimilarRecipeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Similar recipes", fontWeight = FontWeight.Bold)
            Text(text = "You may like these...", color = Color.DarkGray)
        }
        Button(
            onClick = { /*TODO*/ },
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color(0xFFE91E8E)
            )
        ) {
            Text(text = "Show more")
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Composable
fun SimilarRecipes() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        RecipesItem(
            name = "cheesecake",
            category = "desert",
            exp = "beginner",
            img = painterResource(id = R.drawable.hamburger),
            baseColor = Color.Green,
            expColor = Color.Blue
        )
        RecipesItem(
            name = "cheesecake",
            category = "desert",
            exp = "beginner",
            img = painterResource(id = R.drawable.hamburger),
            baseColor = Color.Green,
            expColor = Color.Blue
        )
        RecipesItem(
            name = "cheesecake",
            category = "desert",
            exp = "beginner",
            img = painterResource(id = R.drawable.hamburger),
            baseColor = Color.Green,
            expColor = Color.Blue
        )
        RecipesItem(
            name = "cheesecake",
            category = "desert",
            exp = "beginner",
            img = painterResource(id = R.drawable.hamburger),
            baseColor = Color.Green,
            expColor = Color.Blue
        )
    }

}


@Composable
fun RecipesItem(
    name: String,
    category: String,
    exp: String,
    img: Painter,
    baseColor: Color,
    expColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(CircleShape)
                    .background(baseColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = img, contentDescription = "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(
                text = name,
                modifier = Modifier.weight(0.333f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = category,
                modifier = Modifier.weight(0.333f),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.weight(0.333f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(expColor)
                ) {

                }
                Text(
                    text = exp,
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

        }
        Column(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxHeight()
                .padding(top = 16.dp)
        ) {
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
        }
    }
}