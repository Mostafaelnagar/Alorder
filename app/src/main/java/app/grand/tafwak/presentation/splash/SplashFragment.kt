package app.grand.tafwak.presentation.splash

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import com.structure.base_mvvm.databinding.FragmentSplashBinding
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

  private val viewModel: SplashViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_splash

  override
  fun setUpViews() {
    binding.viewModel = viewModel
    LocaleHelper.setLocale(requireActivity(), Locale("ar"))
    decideNavigationLogic()
  }

  private fun decideNavigationLogic() {
    viewModel.clickEvent.observeForever {
      view?.post {
        navigateSafe(SplashFragmentDirections.actionSplashFragmentToLogInFragment())
      }
//      val targetActivity = when (it) {
//        Constants.FIRST_TIME -> {
//          IntroActivity::class.java
//        }
//        Constants.STUDENT -> {
//          HomeActivity::class.java
//        }
//        Constants.TEACHER -> {
//          TeacherHomeActivity::class.java
//        }
//        else -> {
//          AuthActivity::class.java
//        }
//      }
//      openActivityAndClearStack(targetActivity)

    }
  }
}