package app.grand.tafwak.presentation.home.viewModels

import androidx.databinding.Bindable
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.order_delivery.adapters.OrderCartBodyAdapter
import com.structure.base_mvvm.BR

class ItemSectionViewModel constructor(val sections: Sections) : BaseViewModel() {
  @Bindable
  val adapter: OrderCartBodyAdapter = OrderCartBodyAdapter()

  init {
    setupProduct()
  }

  private fun setupProduct() {
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
}