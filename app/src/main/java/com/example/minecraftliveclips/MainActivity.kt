package com.example.minecraftliveclips

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme

data class ClipItem(
    val title: String,
    val subtitle: String,
    val url: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiuixTheme(colors = darkColorScheme()) {
                MinecraftLiveClipsApp()
            }
        }
    }
}

@Composable
fun MinecraftLiveClipsApp() {
    val context = LocalContext.current
    val scrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    val clips = listOf(
        ClipItem(
            "Minecraft Live – Official Channel",
            "Watch highlights, recaps, and announcements",
            "https://www.youtube.com/@minecraft"
        ),
        ClipItem(
            "Minecraft Live Event Page",
            "Latest event info and streams",
            "https://www.minecraft.net/en-us/live"
        ),
        ClipItem(
            "Minecraft Live 2025 Recap",
            "Catch up on recent reveals and updates",
            "https://www.youtube.com/results?search_query=minecraft+live+recap"
        ),
        ClipItem(
            "Community Clips & Reactions",
            "Browse community highlight clips",
            "https://www.youtube.com/results?search_query=minecraft+live+clips"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Minecraft Live Clips",
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SmallTitle(text = "Highlights & Clips (Miuix)")
            }
            items(clips) { clip ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(clip.url)))
                    },
                    insideMargin = PaddingValues(16.dp)
                ) {
                    Text(text = clip.title)
                    Text(
                        text = clip.subtitle,
                        modifier = Modifier.padding(top = 6.dp),
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary
                    )
                    Text(
                        text = "Open link",
                        modifier = Modifier.padding(top = 10.dp),
                        color = MiuixTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
