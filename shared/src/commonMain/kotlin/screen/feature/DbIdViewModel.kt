package screen.feature

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import screen.arch.UiState
import kotlin.random.Random

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/14 01:22
 */
class DbIdViewModel : ViewModel() {
  private val _saveResult = MutableStateFlow(UiState.initial(false))
  val saveResult = _saveResult.asStateFlow()

  fun saveDbPageId(dbPageId: String) {
    print("saveDbPageId: $dbPageId")

    viewModelScope.launch {
      _saveResult.value = UiState.loading(false)
      withContext(Dispatchers.Default) {
        delay(Random.nextLong(300, 1800))
        if (dbPageId == "1234") {
          _saveResult.value = UiState.success(true)
        } else {
          _saveResult.value = UiState.error("save failed", false)
        }
      }
    }
  }

  fun retry() {
    print("retry")
    _saveResult.value = UiState.initial(false)
  }

}