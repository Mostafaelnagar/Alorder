package app.grand.tafwak.presentation.my_orders.viewModels

import androidx.databinding.Bindable
import app.grand.tafwak.domain.home.models.Companies
import app.grand.tafwak.domain.home.use_case.HomeUseCase
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.my_orders.adapters.MyOrdersAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyOrdersViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {

  @Bindable
  val adapter: MyOrdersAdapter = MyOrdersAdapter()

  init {
    setupMyOrders()
  }


  fun setupMyOrders() {
    val list = ArrayList<Companies>(6).also { list ->
      list.add(
        Companies(
          1,
          "البان"
        )
      )
      list.add(
        Companies(
          2,
          "البان"
        )
      )
      list.add(
        Companies(
          3,
          "البان"
        )
      )
      list.add(
        Companies(
          4,
          "البان"
        )
      )
      list.add(
        Companies(
          5,
          "البان"
        )
      )
      list.add(
        Companies(
          6,
          "البان"
        )
      )

    }
    adapter.differ.submitList(list)
    notifyPropertyChanged(BR.adapter)
  }

}