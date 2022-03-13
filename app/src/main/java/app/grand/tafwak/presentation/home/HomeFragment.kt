package app.grand.tafwak.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.databinding.FragmentHomeBinding
import app.grand.tafwak.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val viewModel: HomeViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.slider.setSliderAdapter(viewModel.homeSliderAdapter)
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
    viewModel.sectionsAdapter.clickEvent.observeForever {
      navigateSafe(HomeFragmentDirections.actionHomeFragmentToSectionDetailsFragment())
    }
    binding.searchHome.setOnClickListener {
      navigateSafe(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }
    binding.tvSuggestedTeachersAll.setOnClickListener {
      navigateSafe(HomeFragmentDirections.actionHomeFragmentToCompaniesFragment())
    }
    binding.icNotification.setOnClickListener {
      navigateSafe(HomeFragmentDirections.actionHomeFragmentToNotificationsFragment())
    }

  }


}