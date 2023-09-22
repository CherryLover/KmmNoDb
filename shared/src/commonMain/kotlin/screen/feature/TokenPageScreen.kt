package screen.feature

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/5 11:56
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
      TokenPageView(onTokenSaved)
    }
  }
}

@Composable
private fun TokenPageView(onTokenSaved: (String) -> Unit) {
  var inputToken by remember { mutableStateOf("") }
  OutlinedTextField(inputToken, {
    inputToken = it
  }, maxLines = 1, label = {
    Text("Input Token")
  })
  Button({
    print("click save token $inputToken")
    onTokenSaved(inputToken)
  }, enabled = inputToken.isNotEmpty()) {
    Text("Next")
  }
}

@Composable
fun TokenPageView_Preview() {
  TokenPageView {

  }
}
