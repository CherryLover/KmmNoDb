package com.cherry.kmm

import LocalWindowSizeClass
import WindowSizeClass
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import calculateWindowSizeClass
import com.arkivanov.decompose.defaultComponentContext
import io.github.xxfast.decompose.LocalComponentContext
import screen.arch.DefaultRootRootComponent
import screen.arch.RootComponent
import screen.feature.DbIdPageScreen
import screen.feature.DbIdViewModel

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    WindowCompat.setDecorFitsSystemWindows(window, false)
//    val rootComponentContext: DefaultComponentContext = defaultComponentContext()
    val root = DefaultRootRootComponent(defaultComponentContext())

    setContent {
      val windowSizeClass: WindowSizeClass = calculateWindowSizeClass(this)
      CompositionLocalProvider(
        LocalComponentContext provides root,
        LocalWindowSizeClass provides windowSizeClass,
      ) {
        NoDbTheme {
          Surface(
            modifier = Modifier.fillMaxSize()
              .systemBarsPadding(),
          ) {
            RootComponent(root)
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TestPreViewRoot() {
  DbIdPageScreen(DbIdViewModel(), {}) {

  }
}