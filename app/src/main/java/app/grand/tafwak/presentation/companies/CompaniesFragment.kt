package app.grand.tafwak.presentation.companies

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.companies.viewModels.CompaniesViewModel
import com.structure.base_mvvm.databinding.FragmentCompaniesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompaniesFragment : BaseFragment<FragmentCompaniesBinding>() {

  private val viewModel: CompaniesViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_companies

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

}