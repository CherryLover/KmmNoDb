package screen.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/8 17:16
 */
@Composable
fun DbIdPageScreen(
  onBack: () -> Unit,
  onDbIdSaved: (String) -> Unit = {}
) {

  Scaffold(Modifier.fillMaxSize(), topBar = {
    TopAppBar(
      backgroundColor = Color.Transparent,
      elevation = 0.dp,
    ) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back",
        tint = MaterialTheme.colors.primary,
        modifier = Modifier
          .padding(8.dp)
          .clickable { onBack() }
      )
      Text(
        "Database Id", color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(start = 10.dp).fillMaxWidth().padding(end = 10.dp),
        fontSize = MaterialTheme.typography.h6.fontSize
      )
    }
  }) {
    Column(
      Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      DbIdPageView(onDbIdSaved)
    }
  }
}

@Composable
private fun DbIdPageView(onDbIdSaved: (String) -> Unit) {
  var dbId by remember { mutableStateOf("") }
  OutlinedTextField(dbId, {
    dbId = it
  }, maxLines = 1, label = {
    Text("Database Id")
  })
  Button({
    print("click save database id $dbId")
    onDbIdSaved(dbId)
  }, enabled = dbId.isNotEmpty()) {
    Text("Verify")
  }
}
