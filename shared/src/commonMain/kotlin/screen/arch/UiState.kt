package screen.arch

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/14 01:37
 */
class UiState<T : Any>(var data: T) {
  var state: UiStateEnum = UiStateEnum.Loading
  var message: String = ""


  companion object {
    fun <T : Any> initial(defaultObj: T): UiState<T> {
      return UiState<T>(defaultObj).apply {
        state = UiStateEnum.initial
      }
    }

    fun <T : Any> loading(defaultObj: T): UiState<T> {
      return UiState<T>(defaultObj).apply {
        state = UiStateEnum.Loading
      }
    }

    fun <T : Any> error(message: String, ignoreValue: T): UiState<T> {
      return UiState(ignoreValue).apply {
        state = UiStateEnum.Error
        this.message = message
      }
    }

    fun <T : Any> success(data: T): UiState<T> {
      return UiState<T>(data).apply {
        state = UiStateEnum.Success
      }
    }
  }
}

enum class UiStateEnum {
  initial, Loading, Error, Success
}