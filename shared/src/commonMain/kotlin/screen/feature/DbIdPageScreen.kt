package screen.feature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import screen.arch.UiState
import screen.arch.UiStateEnum

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/8 17:16
 */
@Composable
fun DbIdPageScreen(
  viewModel: DbIdViewModel, onBack: () -> Unit, onDbIdSaved: (String) -> Unit = {}
) {
  Scaffold(Modifier.fillMaxSize()) {
    Column(
      Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      DbIdPageView(viewModel)
    }
  }
}

@Composable
private fun DbIdPageView(viewModel: DbIdViewModel) {
  val saveResultState by viewModel.saveResult.collectAsState()
  var dbId by remember { mutableStateOf("") }
  OutlinedTextField(dbId, {
    dbId = it
  }, maxLines = 1, label = {
    Text("Database Id")
  }, isError = saveResultState.state == UiStateEnum.Error, )
  Spacer(Modifier.height(10.dp))
  when (saveResultState.state) {
    UiStateEnum.Loading -> {
      CircularProgressIndicator(Modifier.size(24.dp), strokeWidth = 2.dp)
    }

    UiStateEnum.Error -> {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(saveResultState.message, color = Color.Red)
        Button({
          print("click retry")
          dbId = ""
          viewModel.retry()
        }, Modifier.padding(start = 10.dp)) {
          Text("Retry")
        }
      }
    }

    UiStateEnum.Success -> {
      if (saveResultState.data) {
        Text("Save Success", color = Color.Green)
      }
    }

    else -> {
      Button({
        print("click save database id $dbId")
        viewModel.saveDbPageId(dbId)
      }, enabled = dbId.isNotEmpty()) {
        Text("Verify")
      }
    }
  }
}
