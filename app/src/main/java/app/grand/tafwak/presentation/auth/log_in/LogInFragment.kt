package app.grand.tafwak.presentation.auth.log_in

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import codes.grand.pretty_pop_up.PrettyPopUpHelper
import app.grand.tafwak.domain.auth.enums.AuthFieldsValidation
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.base.utils.getDeviceId
import app.grand.tafwak.presentation.base.utils.showNoApiErrorAlert
import com.structure.base_mvvm.databinding.FragmentLogInBinding
import app.grand.tafwak.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>() {

  private val viewModel: LogInViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_log_in

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    viewModel.request.device_token = getDeviceId(requireActivity())
  }

  override
  fun setupObservers() {
//    binding.btnPhone.setOnClickListener {
//      binding.progressPercentage.progress = binding.progressPercentage.progress + 10
//    }

    viewModel.clickEvent.observe(this) {
      when (it) {
        Constants.FORGET_PASSWORD -> openForgotPassword()
        Constants.REGISTER -> openSignUp()
        Constants.CONTINUE_PROGRESS -> openHome()
      }

    }


    lifecycleScope.launchWhenResumed {
      viewModel.logInResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(
              it,
              retryAction = { viewModel.onLogInClicked() },
              notActiveAction = { openConfirm() })
          }

        }
      }
    }
  }

  private fun openForgotPassword() {
    navigateSafe(LogInFragmentDirections.actionOpenForgotPasswordFragment())
  }

  private fun openConfirm() {
    navigateSafe(
      LogInFragmentDirections.actionLogInFragmentToFragmentConfirmCode(
        viewModel.request.email,
        Constants.Verify
      )
    )
  }

  private fun openSignUp() {
    navigateSafe(LogInFragmentDirections.actionOpenSignUpFragment())
  }

  private fun openHome() {
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }

}