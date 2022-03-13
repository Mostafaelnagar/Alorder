package app.grand.tafwak.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.domain.profile.entity.AvatarResponse
import app.grand.tafwak.presentation.profile.viewModels.ItemAvatarViewModel
import com.github.islamkhsh.CardSliderAdapter
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemAvatarBinding

class AvatarAdapter : CardSliderAdapter<AvatarAdapter.ViewHolder>() {
  var lastSelected = -1
  private val differCallback = object : DiffUtil.ItemCallback<AvatarResponse>() {
    override fun areItemsTheSame(oldItem: AvatarResponse, newItem: AvatarResponse): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AvatarResponse, newItem: AvatarResponse): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
    return ViewHolder(view)
  }

  override fun bindVH(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    val itemViewModel = ItemAvatarViewModel(data)
    itemViewModel.clickEvent.observeForever {
      lastSelected = data.id
    }
    holder.setViewModel(itemViewModel)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  override fun onViewAttachedToWindow(holder: ViewHolder) {
    super.onViewAttachedToWindow(holder)
    holder.bind()
  }

  override fun onViewDetachedFromWindow(holder: ViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.unBind()
  }


  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private lateinit var itemLayoutBinding: ItemAvatarBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemAvatarViewModel) {
      itemLayoutBinding.itemViewModels = itemViewModel
    }
  }

}