package app.grand.tafwak.presentation.home.viewModels

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {

  @Bindable
  val adapter: CompaniesAdapter = CompaniesAdapter()

  @Bindable
  val sectionsAdapter: SectionsAdapter = SectionsAdapter()

  val homeSliderAdapter: HomeSliderAdapter = HomeSliderAdapter()

  private val _homeResponse =
    MutableStateFlow<Resource<BaseResponse<HomeStudentData>>>(Resource.Default)
  val homeResponse = _homeResponse

  init {
    setupSlider()
    setupCompanies()
    setupSections()
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


  fun setupSlider() {
    val list = ArrayList<Slider>().also { list ->
      list.add(
        Slider(
          1,
          "http://protenchef.golden-info.com/uploads/Offer/first_offer.png"
        )
      )
      list.add(
        Slider(
          1,
          "http://protenchef.golden-info.com/uploads/Offer/first_offer.png"
        )
      )

    }
    homeSliderAdapter.update(list)

  }

  fun setupCompanies() {
    val list = ArrayList<Companies>(6).also { list ->
      list.add(
        Companies(
          1,
          "وادى فود"
        )
      )
      list.add(
        Companies(
          2,
          "وادى فود"
        )
      )
      list.add(
        Companies(
          3,
          "وادى فود"
        )
      )
      list.add(
        Companies(
          4,
          "وادى فود"
        )
      )
      list.add(
        Companies(
          5,
          "وادى فود"
        )
      )
      list.add(
        Companies(
          6,
          "وادى فود"
        )
      )

    }
    adapter.differ.submitList(list)
    notifyPropertyChanged(BR.adapter)
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
      list.add(
        Sections(
          3,
          "البان"
        )
      )
      list.add(
        Sections(
          4,
          "البان"
        )
      )
      list.add(
        Sections(
          5,
          "البان"
        )
      )
      list.add(
        Sections(
          6,
          "البان"
        )
      )

    }
    sectionsAdapter.differ.submitList(list)
    notifyPropertyChanged(BR.sectionsAdapter)
  }

}