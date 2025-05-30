import android.media.MediaPlayer
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterIntroScreen(
    @DrawableRes drawable: Int,
    onContinue: () -> Unit
) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.menu_theme_2)
    }

    DisposableEffect(Unit) {
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    val essay1 = stringResource(R.string.essay1)
    val essay2 = stringResource(R.string.essay2)
    val essays = listOf(essay1, essay2)

    val essayChunks: List<List<String>> = remember(essays) {
        essays.map { essay ->
            essay.split("\\s+".toRegex())
                .chunked(260)
                .map { it.joinToString(" ") }
        }
    }

    var currentEssay by remember { mutableStateOf(0) }
    var currentChunk by remember { mutableStateOf(0) }
    var allText by remember { mutableStateOf("") }
    val chunkText = essayChunks[currentEssay][currentChunk]
    val scrollState = rememberScrollState()

    LaunchedEffect(currentEssay, currentChunk) {
        // Reset on first chunk, append on subsequent
        if (currentEssay == 0 && currentChunk == 0) {
            allText = ""
        } else {
            allText += "\n\n"
        }
        for (char in chunkText) {
            allText += char
            delay(15L)
        }
    }

    Scaffold(
        bottomBar = {
            StatusBar(
                lives = 3,
                maxLives = 5,
                mana = 2,
                maxMana = 5,
                centerDrawable = drawable,
                onCenterDrawableClick = {
                    val chunks = essayChunks[currentEssay]
                    if (currentChunk < chunks.lastIndex) {
                        currentChunk++
                    } else if (currentEssay < essayChunks.lastIndex) {
                        currentEssay++
                        currentChunk = 0
                    } else {
                        onContinue()
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Text(text = allText)
            }
        }
    }
}

@Composable
fun StatusBar(
    lives: Int,
    maxLives: Int,
    mana: Int,
    maxMana: Int,
    @DrawableRes centerDrawable: Int,
    onCenterDrawableClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row { Text(text = "Health: $lives/$maxLives") }

        Image(
            painter = painterResource(centerDrawable),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clickable { onCenterDrawableClick() }
        )

        Row { Text(text = "Mana: $mana/$maxMana") }
    }
}