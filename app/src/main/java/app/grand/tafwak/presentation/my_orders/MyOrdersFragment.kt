package app.grand.tafwak.presentation.my_orders

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.my_orders.viewModels.MyOrdersViewModel
import com.structure.base_mvvm.databinding.FragmentMyOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOrdersFragment : BaseFragment<FragmentMyOrdersBinding>() {

  private val viewModel: MyOrdersViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_my_orders

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.toolbar.navigationIcon = null
  }

  override
  fun setupObservers() {
    viewModel.adapter.clickEvent.observeForever {

      navigateSafe(MyOrdersFragmentDirections.actionMyOrdersFragmentToMyOrderDetailsFragment())
    }
  }


}