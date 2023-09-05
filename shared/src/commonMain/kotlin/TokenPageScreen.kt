/**
 * @description
 * @author: Created jiangjiwei in 2023/9/5 11:56
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TokenPageScreen(
  onBack: () -> Unit,
) {
  Scaffold(Modifier.fillMaxSize().background(Color.Red)) {
    Column(Modifier.fillMaxSize()) {
      Text(text = "TokenPageScreen-Top", color = Color.Blue)
      Spacer(Modifier.fillMaxWidth().weight(1f))
      Text(text = "TokenPageScreen-Bottom", color = Color.Blue)
    }
  }
}