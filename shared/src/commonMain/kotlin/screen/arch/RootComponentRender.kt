package screen.arch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.push
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import screen.feature.DbIdPageScreen
import screen.feature.DbIdViewModel
import screen.feature.TokenPageScreen
import screen.router.HomeScreen

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/11 10:34
 */
@Composable
fun RootComponent(root: DefaultRootRootComponent) {
  val childStack by root.stack.subscribeAsState()
  var hasBack by remember { mutableStateOf(true) }

  Column(Modifier.background(Color.White)) {
    val instance = childStack.active.instance
    hasBack = instance.hasBack
    DbToolbar(instance, hasBack) {
      root.onBackClick()
    }
    Children(
      stack = childStack,
      animation = stackAnimation(slide())
    ) {
      when (val child = it.instance) {
        is DbRootComponent.Child.NbTokenChild -> {
          TokenPageScreen({

          }, {
            root.navigation.push(HomeScreen.DbIdPage)
          })
        }

        is DbRootComponent.Child.NbPageIdChild -> {
          val viewModel = getViewModel(Unit, viewModelFactory { DbIdViewModel() })
          DbIdPageScreen(viewModel, {
            root.onBackClick()
          }, {

          })
        }
      }
    }
  }
}

@Composable
fun DbToolbar(instance: DbRootComponent.Child, hasBack: Boolean, onBack: () -> Unit) {
  var pageTitle by remember { mutableStateOf("") }

  LaunchedEffect(instance) {
    pageTitle = when (instance) {
      is DbRootComponent.Child.NbTokenChild -> {
        "Notion Token"
      }

      is DbRootComponent.Child.NbPageIdChild -> "Notion Page Id"
    }
  }
  TopAppBar(
    backgroundColor = Color.Transparent,
    elevation = 0.dp,
  ) {
    if (hasBack) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back",
        tint = MaterialTheme.colors.primary,
        modifier = Modifier
          .clickable {
            onBack()
          }
          .padding(8.dp)
      )
    }
    val modifier = if (hasBack) Modifier.padding(start = 10.dp) else Modifier.padding(horizontal = 20.dp)
    Text(
      "${pageTitle}-Debug", color = MaterialTheme.colors.primary,
      modifier = modifier.fillMaxWidth(),
      fontSize = MaterialTheme.typography.h6.fontSize
    )
  }
}
