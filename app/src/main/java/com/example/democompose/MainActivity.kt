package com.example.democompose

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import com.example.democompose.ui.theme.DemoComposeTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
            windowInsetsController!!.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            myVideoPlayer()
    }
}

    @Composable
    fun myVideoPlayer(){
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
        ) {
        val localContext= LocalContext.current
           val exoPlayer= remember {
               SimpleExoPlayer.Builder(localContext).build().apply {
                   val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                       localContext,
                       Util.getUserAgent(localContext, localContext.packageName)
                   )
                   val mediaItem = MediaItem.fromUri("android.resource://" + getPackageName() + "/" + R.raw.fue_intro_video)
                   val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                       .createMediaSource(mediaItem)
                   this.setMediaSource(source)
                   repeatMode= Player.REPEAT_MODE_ALL
                   this.prepare()
               }
           }
            AndroidView(factory = {
                PlayerView(it).apply {
                    player=exoPlayer
                    layoutParams= FrameLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT)
                    useController=false
                    resizeMode=RESIZE_MODE_ZOOM
                    (player as SimpleExoPlayer).playWhenReady=true
                }
            })
        }
    }
}
