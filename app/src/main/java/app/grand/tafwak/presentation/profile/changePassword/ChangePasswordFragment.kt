package app.grand.tafwak.presentation.profile.changePassword

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.backToPreviousScreen
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.utils.showMessage
import app.grand.tafwak.presentation.base.utils.showSuccessAlert
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentUpdatePasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentUpdatePasswordBinding>() {

  private val viewModel: ChangePasswordViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_update_password

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.clickEvent.observeForever { backToPreviousScreen() }
    lifecycleScope.launchWhenResumed {
      viewModel.changePasswordResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            backToPreviousScreen()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.changePassword() })
          }
          Resource.Default -> {
          }
        }
      }
    }
  }


}