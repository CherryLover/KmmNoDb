import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import io.github.xxfast.decompose.LocalComponentContext
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun HomeScreenUI() {
  val router: Router<HomeScreen> = rememberRouter(HomeScreen::class, stack = listOf(HomeScreen.TokenPage))

  RoutedContent(
    router = router,
    animation = predictiveBackAnimation(
      backHandler = LocalComponentContext.current.backHandler,
      onBack = { router.pop() },
      animation = stackAnimation(slide())
    ),
  ) { screen ->
    when (screen) {
      HomeScreen.TokenPage -> TokenPageScreen {
        router.pop()
      }
      else -> {

      }
    }
  }
}
