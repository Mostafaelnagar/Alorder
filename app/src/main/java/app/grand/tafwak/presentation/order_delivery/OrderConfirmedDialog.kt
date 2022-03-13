package app.grand.tafwak.presentation.order_delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.OrderSuccessDialogBinding

class OrderConfirmedDialog : DialogFragment() {
  lateinit var binding: OrderSuccessDialogBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.order_success_dialog, container, false)
    binding.confirm.setOnClickListener { dismiss() }
    return binding.root
  }

}