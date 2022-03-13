package app.grand.tafwak.presentation.my_orders

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.my_orders.viewModels.MyOrderDetailsViewModel
import com.structure.base_mvvm.databinding.FragmentOrderDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOrderDetailsFragment : BaseFragment<FragmentOrderDetailsBinding>() {

  private val viewModel: MyOrderDetailsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_order_details

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {

  }


}