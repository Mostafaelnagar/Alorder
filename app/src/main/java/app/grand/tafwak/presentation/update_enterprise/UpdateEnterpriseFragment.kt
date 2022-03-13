package app.grand.tafwak.presentation.update_enterprise

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.update_enterprise.viewModel.UpdateEnterpriseViewModel
import com.structure.base_mvvm.databinding.FragmentUpdateEnterpriseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UpdateEnterpriseFragment : BaseFragment<FragmentUpdateEnterpriseBinding>() {

  private val viewModel: UpdateEnterpriseViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_update_enterprise

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {

    lifecycleScope.launchWhenResumed {
      viewModel.registerResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
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

}