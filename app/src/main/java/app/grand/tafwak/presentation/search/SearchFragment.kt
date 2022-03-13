package app.grand.tafwak.presentation.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.databinding.FragmentHomeBinding
import app.grand.tafwak.presentation.home.viewModels.HomeViewModel
import app.grand.tafwak.presentation.search.viewModels.SearchViewModel
import com.structure.base_mvvm.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

  private val viewModel: SearchViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_search

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

}