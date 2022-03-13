package app.grand.tafwak.presentation.auth.sign_up

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.base.utils.getDeviceId
import com.structure.base_mvvm.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

  private val viewModel: SignUpViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_sign_up

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    viewModel.registerRequest.device_token = getDeviceId(requireActivity())
  }

  override
  fun setupObservers() {
    viewModel.clickEvent.observe(this) {
      when (it) {
        Constants.CONFIRM_CODE -> openConfirmCode()
        Constants.REVIEW_DIALOG -> openDialog()
      }
    }

    lifecycleScope.launchWhenResumed {
      viewModel.registerResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openConfirmCode()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.register() })
          }
          Resource.Default -> {
          }
        }
      }
    }
  }

  private fun openConfirmCode() {
    navigateSafe(
      SignUpFragmentDirections.actionOpenConfirmCodeFragment(
        viewModel.registerRequest.email,
        Constants.Verify
      )
    )
  }

  private fun openDialog() {
    navigateSafe(
      SignUpFragmentDirections.actionSignUpFragmentToDetectLocationDialog()
    )
  }
}