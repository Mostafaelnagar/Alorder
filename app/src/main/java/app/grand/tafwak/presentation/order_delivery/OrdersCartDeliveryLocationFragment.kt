package app.grand.tafwak.presentation.order_delivery

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.order_delivery.viewModel.OrdersCartViewModel
import com.structure.base_mvvm.databinding.FragmentOrderCartDeliveryLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OrdersCartDeliveryLocationFragment :
  BaseFragment<FragmentOrderCartDeliveryLocationBinding>() {

  private val viewModel: OrdersCartViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_order_cart_delivery_location

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
    binding.btnConfirm.setOnClickListener {
      navigateSafe(OrdersCartDeliveryLocationFragmentDirections.actionOrdersCartDeliveryLocationFragmentToOrdersCartFragment())
    }
  }

}