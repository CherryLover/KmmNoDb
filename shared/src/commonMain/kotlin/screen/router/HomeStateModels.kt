package screen.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/5 11:54
 */
@Parcelize
sealed class HomeScreen: Parcelable {
  object TokenPage: HomeScreen()

  object DbIdPage: HomeScreen()
}