package screen.arch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.statekeeper.StateKeeper
import screen.router.HomeScreen

/**
 * @description
 * @author: Created jiangjiwei in 2023/9/11 09:54
 */
interface DbRootComponent {

  val stack: Value<ChildStack<*, Child>>

  fun onBackClick(toIndex: Int = 0)

  sealed class Child(
    var hasBack: Boolean,
  ) {
    class NbTokenChild(component: NbTokenComponent) : Child(false)
    class NbPageIdChild(component: NbPageIdComponent) : Child(true)
  }
}

interface DbComponent : ComponentContext {
  val backCallback: BackCallback
}

class DefaultRootRootComponent(
  context: ComponentContext
) : DbRootComponent, ComponentContext by context {
  val navigation = StackNavigation<HomeScreen>()

  override val stack = childStack(
    source = navigation,
    initialConfiguration = HomeScreen.TokenPage,
    handleBackButton = true,
    childFactory = { screen, context ->
      val useComponent = object : DbComponent {
        override val backCallback: BackCallback
          get() = object : BackCallback(isEnabled = true, priority = 0) {
            override fun onBack() {
              navigation.pop()
            }
          }
        override val backHandler: BackHandler
          get() = context.backHandler
        override val instanceKeeper: InstanceKeeper
          get() = context.instanceKeeper
        override val lifecycle: Lifecycle
          get() = context.lifecycle
        override val stateKeeper: StateKeeper
          get() = context.stateKeeper
      }
      useComponent.backHandler.register(useComponent.backCallback)
      childFactory(screen, useComponent)
    }
  )

  override fun onBackClick(toIndex: Int) {
    if (toIndex == 0) {
      navigation.pop()
      return
    }
    navigation.popTo(toIndex)
  }

  private fun childFactory(
    screen: HomeScreen,
    context: DbComponent,
  ): DbRootComponent.Child {
    return when (screen) {
      HomeScreen.TokenPage -> {
        context.backCallback.isEnabled = false
        val component = NbTokenComponent(context)
        DbRootComponent.Child.NbTokenChild(component)
      }

      HomeScreen.DbIdPage -> {
        val component = NbPageIdComponent(context)
        DbRootComponent.Child.NbPageIdChild(component)
      }
    }
  }
}

class NbPageIdComponent(
  val context: DbComponent
) : ComponentContext by context {

}

class NbTokenComponent(
  val context: DbComponent
) : ComponentContext by context {

}