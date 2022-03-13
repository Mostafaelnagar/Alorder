package app.grand.tafwak.presentation.auth.confirmCode

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import com.structure.base_mvvm.databinding.FragmentConfirmCodeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ConfirmCodeFragment : BaseFragment<FragmentConfirmCodeBinding>() {

  private val viewModel: ConfirmViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_confirm_code

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.verifyResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            if (viewModel.request.type == "verify")
              openCountry()
            else
              openChangePassword()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.verifyAccount() })
          }
          Resource.Default -> {
          }
        }
      }
    }
    viewModel.clickEvent.observeForever { openChangePassword() }
  }

  private fun openCountry() {
    navigateSafe(ConfirmCodeFragmentDirections.actionFragmentConfirmCodeToCountriesFragment())
  }

  private fun openChangePassword() {
    navigateSafe(ConfirmCodeFragmentDirections.actionFragmentConfirmCodeToChangePasswordFragment())
  }


}