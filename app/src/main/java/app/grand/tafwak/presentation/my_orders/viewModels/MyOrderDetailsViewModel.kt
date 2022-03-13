package app.grand.tafwak.presentation.my_orders.viewModels

import androidx.databinding.Bindable
import app.grand.tafwak.domain.home.models.Companies
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.domain.home.use_case.HomeUseCase
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.my_orders.adapters.MyOrdersAdapter
import app.grand.tafwak.presentation.order_delivery.adapters.CartAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyOrderDetailsViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {

  @Bindable
  val adapter: CartAdapter = CartAdapter()

  init {
    setupMyOrders()
  }


  fun setupMyOrders() {
    val list = ArrayList<Sections>(6).also { list ->
      list.add(
        Sections(
          1,
          "البان"
        )
      )
      list.add(
        Sections(
          2,
          "البان"
        )
      )
      list.add(
        Sections(
          3,
          "البان"
        )
      )

    }
    adapter.differ.submitList(list)
    notifyPropertyChanged(BR.adapter)
  }

}