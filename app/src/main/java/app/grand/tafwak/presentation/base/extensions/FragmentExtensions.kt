package app.grand.tafwak.presentation.base.extensions

import android.app.Activity
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import app.grand.tafwak.domain.utils.FailureStatus
import app.grand.tafwak.domain.utils.Resource.Failure
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.auth.AuthActivity
import app.grand.tafwak.presentation.base.utils.*

fun Fragment.handleApiError(
  failure: Failure,
  retryAction: (() -> Unit)? = null,
  noDataAction: (() -> Unit)? = null,
  notActive: (() -> Unit)? = null,
  notActiveAction: (() -> Unit)? = null,
  noInternetAction: (() -> Unit)? = null
) {
  when (failure.failureStatus) {
    FailureStatus.EMPTY -> {
      noDataAction?.invoke()
      failure.message?.let { showNoApiErrorAlert(requireActivity(), it) }
    }
    FailureStatus.NO_INTERNET -> {
      noInternetAction?.invoke()
      showNoInternetAlert(requireActivity())
    }
    FailureStatus.NOT_ACTIVE -> {
      notActive?.invoke()
      failure.message?.let {
        showNoApiErrorAlertWithAction(
          requireActivity(),
          it,
          getString(R.string.active),
          notActiveAction
        )
      }
    }
    FailureStatus.TOKEN_EXPIRED -> {
      openActivityAndClearStack(AuthActivity::class.java)
    }
    FailureStatus.OTHER ->{
      requireView().showSnackBar(resources.getString(R.string.not_match_password))
    }
    else -> {
      noDataAction?.invoke()
      requireView().showSnackBar(
        failure.message ?: resources.getString(R.string.some_error),
        resources.getString(R.string.retry),
        retryAction
      )
    }
  }
}

fun Fragment.hideKeyboard() = hideSoftInput(requireActivity())

fun Fragment.showNoInternetAlert() = showNoInternetAlert(requireActivity())

fun Fragment.showMessage(message: String?) = showMessage(requireContext(), message)

fun Fragment.showError(
  message: String,
  retryActionName: String? = null,
  action: (() -> Unit)? = null
) =
  requireView().showSnackBar(message, retryActionName, action)

fun Fragment.getMyColor(@ColorRes id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.getMyDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)!!
fun Fragment.getMyDrawableVector(@DrawableRes id: Int) =
  ContextCompat.getDrawable(requireContext(), id)!!

fun Fragment.getMyString(id: Int) = resources.getString(id)

fun <A : Activity> Fragment.openActivityAndClearStack(activity: Class<A>) {
  requireActivity().openActivityAndClearStack(activity)
}

fun <A : Activity> Fragment.openActivity(activity: Class<A>) {
  requireActivity().openActivity(activity)
}

fun <T> Fragment.getNavigationResultLiveData(key: String = "result") =
  findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.removeNavigationResultObserver(key: String = "result") =
  findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
  findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun Fragment.onBackPressedCustomAction(action: () -> Unit) {
  requireActivity().onBackPressedDispatcher.addCallback(
    viewLifecycleOwner,
    object : OnBackPressedCallback(true) {
      override
      fun handleOnBackPressed() {
        action()
      }
    }
  )
}

fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
  findNavController().navigate(directions, navOptions)
}

fun Fragment.backToPreviousScreen() {
  Log.e("backToPreviousScreen", "backToPreviousScreen: ")
  findNavController().navigateUp()
}