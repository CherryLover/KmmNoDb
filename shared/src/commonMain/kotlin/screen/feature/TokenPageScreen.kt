package screen.feature

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/5 11:56
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TokenPageScreen(
  onBack: () -> Unit,
  onTokenSaved: (String) -> Unit = {}
) {

  Scaffold(Modifier.fillMaxSize()) {
    Column(
      Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      TokenPageView()
    }
  }
}

@Composable
private fun TokenPageView() {
  var inputToken by remember { mutableStateOf("") }
  TextField(inputToken, {
    inputToken = it
  }, Modifier.background(androidx.compose.ui.graphics.Color.White), shape = RoundedCornerShape(50), maxLines = 1, label = {
    Text("Input Token")
  })
  Button({
    print("click save token $inputToken")
  }, enabled = inputToken.isNotEmpty()) {
    Text("Save Token")
  }
}

@Composable
fun TokenPageView_Preview() {
  TokenPageView()
}
