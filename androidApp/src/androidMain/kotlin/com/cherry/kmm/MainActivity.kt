package com.cherry.kmm

import HomeScreenUI
import LocalWindowSizeClass
import WindowSizeClass
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import calculateWindowSizeClass
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import io.github.xxfast.decompose.LocalComponentContext

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    WindowCompat.setDecorFitsSystemWindows(window, false)
    val rootComponentContext: DefaultComponentContext = defaultComponentContext()

    setContent {
      val windowSizeClass: WindowSizeClass = calculateWindowSizeClass(this)
      CompositionLocalProvider(
        LocalComponentContext provides rootComponentContext,
        LocalWindowSizeClass provides windowSizeClass,
      ) {
        NoDbTheme {
          Surface(
            modifier = Modifier.fillMaxSize()
              .systemBarsPadding(),
          ) {
            HomeScreenUI()
          }
        }
      }
    }
  }
}