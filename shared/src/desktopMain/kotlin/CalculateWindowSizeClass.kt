import CommonWindowSizeClass.Companion.calculateFromSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowState

@Composable
fun calculateWindowSizeClass(windowState: WindowState): WindowSizeClass = calculateFromSize(windowState.size)
