package app.grand.tafwak.presentation.section_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.section_details.viewModels.SectionDetailsViewModel
import com.structure.base_mvvm.databinding.FragmentSectionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>() {

  private val viewModel: SectionDetailsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_section_details

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {

    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect {
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
            handleApiError(it)
          }
        }
      }
    }
    binding.orderTotalContainer.setOnClickListener {
      navigateSafe(SectionDetailsFragmentDirections.actionSectionDetailsFragmentToCartFragment())
    }
  }

}