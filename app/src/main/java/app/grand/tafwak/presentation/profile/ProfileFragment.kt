package app.grand.tafwak.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.utils.Constants
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.backToPreviousScreen
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.utils.showSuccessAlert
import com.structure.base_mvvm.databinding.FragmentProfileBinding
import app.grand.tafwak.presentation.profile.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

  private val viewModel: ProfileViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_profile

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.clickEvent.observe(this) {
      when (it) {
        Constants.BACK -> backToPreviousScreen()
        Constants.PICK_IMAGE -> singleTedBottomPicker(requireActivity())
      }

    }
    selectedImages.observeForever { result ->
      result.path?.let { path ->
        viewModel.request.setImage(path, Constants.IMAGE)
//        binding.userImg.setImageURI(result)
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.profileResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(
              it,
              retryAction = { viewModel.updateProfile() })
          }

        }
      }
    }
  }


}