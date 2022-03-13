package app.grand.tafwak.presentation.account

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.home.models.Companies
import codes.grand.pretty_pop_up.PrettyPopUpHelper
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.auth.AuthActivity
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.base.utils.Constants
import com.structure.base_mvvm.BR
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {

  private val viewModelTeacher: AccountViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_account

  override
  fun setBindingVariables() {
    binding.viewModel = viewModelTeacher
    setupAccount()
  }

  override
  fun setupObservers() {
//    viewModelTeacher.clickEvent.observe(this) { action ->
//      when (action) {
//        Constants.PROFILE -> {
//          openProfileScreen()
//        }
//
//        else -> showLogOutPopUp()
//      }
//    }
//    lifecycleScope.launchWhenResumed {
//      viewModelTeacher.logOutResponse.collect {
//        when (it) {
//          Resource.Loading -> {
//            hideKeyboard()
//            showLoading()
//          }
//          is Resource.Success -> {
//            hideLoading()
//            viewModelTeacher.clearStorage()
//            openLogInScreen()
//          }
//          is Resource.Failure -> {
//            hideLoading()
//            handleApiError(it)
//          }
//        }
//      }
//    }
    viewModelTeacher.accountAdapter.clickEvent.observeForever {
      navigateSafe(it.action)
    }
  }

  fun setupAccount() {
    val list = ArrayList<AccountItem>(6).also { list ->
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_profile),
          getString(R.string.profile_contact),
          getString(R.string.profile_contact),
          AccountFragmentDirections.actionAccountFragmentToProfileFragment()
        )
      )
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_account_favorite),
          getString(R.string.favorite),
          getString(R.string.favorite_body),
          AccountFragmentDirections.actionAccountFragmentToFavoriteFragment()
        )
      )
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_lock),
          getString(R.string.password_account),
          getString(R.string.change_password),
          AccountFragmentDirections.actionAccountFragmentToUpdatePasswordFragment()
        )
      )
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_account_home),
          getString(R.string.tv_building_info),
          getString(R.string.tv_building_info_change),
          AccountFragmentDirections.actionAccountFragmentToUpdateEnterpriseFragment()
        )
      )
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_credit_card),
          getString(R.string.payment_account),
          "1990 جنيه مصرى",
          AccountFragmentDirections.actionAccountFragmentToProfileFragment()
        )
      )
      list.add(
        AccountItem(
          getMyDrawable(R.drawable.ic_support),
          getString(R.string.support),
          "",
          AccountFragmentDirections.actionAccountFragmentToProfileFragment()
        )
      )


    }
    viewModelTeacher.accountAdapter.differ.submitList(list)
    viewModelTeacher.notifyPropertyChanged(BR.accountAdapter)
  }

  private fun showLogOutPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle(R.string.log_out)
      .setTitleColor(getMyColor(R.color.colorPrimaryDark))
      .setContent(R.string.log_out_hint)
      .setContentColor(getMyColor(R.color.gray))
      .setPositiveButtonBackground(R.color.colorPrimaryDark)
      .setPositiveButtonTextColor(getMyColor(R.color.white))
      .setImage(R.drawable.ic_logout)
      .setPositiveButton(R.string.log_out) {
        it.dismiss()
        viewModelTeacher.logOut()
      }
      .setNegativeButtonBackground(R.drawable.btn_gray)
      .setNegativeButtonTextColor(getMyColor(R.color.white))
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }

  private fun openLogInScreen() {
    requireActivity().openActivityAndClearStack(AuthActivity::class.java)
  }

  private fun openProfileScreen() {
    navigateSafe(AccountFragmentDirections.actionAccountFragmentToProfileFragment())
  }
}