package app.grand.tafwak.presentation.auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.DetectLocationDialogBinding

class DetectLocationDialog : BottomSheetDialogFragment() {
  lateinit var binding: DetectLocationDialogBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.detect_location_dialog, container, false)
    binding.confirm.setOnClickListener { dismiss() }
    return binding.root
  }

}