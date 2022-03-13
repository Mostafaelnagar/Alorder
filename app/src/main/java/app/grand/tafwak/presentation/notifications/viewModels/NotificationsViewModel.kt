package app.grand.tafwak.presentation.notifications.viewModels

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.home.models.Companies
import app.grand.tafwak.domain.home.models.HomeStudentData
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.domain.home.models.Slider
import app.grand.tafwak.domain.home.use_case.HomeUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.BR
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.home.adapters.SectionsAdapter
import app.grand.tafwak.presentation.home.adapters.HomeSliderAdapter
import app.grand.tafwak.presentation.home.adapters.CompaniesAdapter
import app.grand.tafwak.presentation.notifications.adapters.NotificationsAdapter
import app.grand.tafwak.presentation.search.adapter.SearchAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {

  @Bindable
  val adapter: NotificationsAdapter = NotificationsAdapter()

  init {
    setupSections()
  }

  fun setupSections() {
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
    }
    adapter.differ.submitList(list)
    notifyPropertyChanged(BR.sectionsAdapter)
  }

}