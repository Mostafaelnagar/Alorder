package app.grand.tafwak.presentation.order_delivery.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.home.models.HomeStudentData
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.domain.home.use_case.HomeUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.order_delivery.adapters.CartAdapter
import app.grand.tafwak.presentation.order_delivery.adapters.OrdersCartHeaderAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OrdersCartViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {

  @Bindable
  val adapter: OrdersCartHeaderAdapter = OrdersCartHeaderAdapter()

  @Bindable
  val cartAdapter: CartAdapter = CartAdapter()

  private val _homeResponse =
    MutableStateFlow<Resource<BaseResponse<HomeStudentData>>>(Resource.Default)
  val homeResponse = _homeResponse

  init {
    setupOrder()
    setupCart()
  }


  private fun getHomeStudent() {
    homeUseCase.homeService()
      .onEach { result ->
        _homeResponse.value = result
      }
      .launchIn(viewModelScope)

  }


  var homeStudentData: HomeStudentData = HomeStudentData()
//    set(value) {
//      adapter.differ.submitList(value.sections)
//      notifyPropertyChanged(BR.adapter)
//      sectionsAdapter.differ.submitList(value.aClasses)
//      notifyPropertyChanged(BR.groupsAdapter)
//      homeSliderAdapter.update(value.sliders)
//      field = value
//    }

  private fun setupOrder() {
    val list = ArrayList<Sections>(3).also { list ->
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
private fun setupCart() {
    val list = ArrayList<Sections>(3).also { list ->
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
    cartAdapter.differ.submitList(list)
    notifyPropertyChanged(BR.cartAdapter)
  }

}