package app.grand.tafwak.presentation.favorite

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.favorite.viewModels.FavoriteViewModel
import com.structure.base_mvvm.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

  private val viewModel: FavoriteViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_favorite

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

}