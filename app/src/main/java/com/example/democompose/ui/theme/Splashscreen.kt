package com.example.democompose.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.example.democompose.R

class Splashscreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
            windowInsetsController!!.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            Surface(color = MaterialTheme.colors.background) {
                
            }
//            imageSetForSplash()
        }
    }
    @Composable
    private fun imageSetForSplash(navController: NavController) {
        val scale= remember {
            androidx.compose.animation.core.Animatable(0f)
        }
        LaunchedEffect(key1 = true){
            scale.animateTo(targetValue = 0.3f,)
        }
      Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
         Image(painter = painterResource(id = R.drawable.logo,),
             contentDescription = "Logo")
      }
    }
}