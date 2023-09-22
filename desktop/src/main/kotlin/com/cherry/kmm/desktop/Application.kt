package com.cherry.kmm.desktop

import LocalWindowSizeClass
import WindowSizeClass
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import calculateWindowSizeClass
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.LocalComponentContext
import screen.arch.DefaultRootRootComponent
import screen.arch.RootComponent

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
  val lifecycle = LifecycleRegistry()
  val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
  val root = DefaultRootRootComponent(rootComponentContext)

  application {
    val windowState: WindowState = rememberWindowState()
    val windowSizeClass: WindowSizeClass = calculateWindowSizeClass(windowState)
    LifecycleController(lifecycle, windowState)
//    appStorage = AppDirsFactory.getInstance()
//      .getUserDataDir("io.github.xxfast.nytimes", "1.0.0", "xxfast")


    Window(
      title = "KmmNoDb",
      state = windowState,
      onCloseRequest = { exitApplication() }
    ) {
      CompositionLocalProvider(
        LocalComponentContext provides rootComponentContext,
        LocalWindowSizeClass provides windowSizeClass,
      ) {
        MaterialTheme {
          RootComponent(root)
        }
      }
    }
  }
}
