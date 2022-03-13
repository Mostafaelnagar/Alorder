package app.grand.tafwak.presentation.notifications

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.notifications.viewModels.NotificationsViewModel
import com.structure.base_mvvm.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

  private val viewModel: NotificationsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_notifications

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

}