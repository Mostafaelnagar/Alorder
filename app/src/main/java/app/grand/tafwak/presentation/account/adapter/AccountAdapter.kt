package app.grand.tafwak.presentation.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.domain.home.models.Sections
import app.grand.tafwak.presentation.account.AccountItem
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.utils.SingleLiveEvent
import com.structure.base_mvvm.databinding.ItemAccountBinding

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
  var clickEvent: SingleLiveEvent<AccountItem> = SingleLiveEvent()

  private val differCallback = object : DiffUtil.ItemCallback<AccountItem>() {
    override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
      return oldItem.icon == newItem.icon
    }

    override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    val itemViewModel = ItemAccountViewModel(data)
    itemViewModel.clickEvent.observeForever {
      clickEvent.value = data
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
    private lateinit var itemLayoutBinding: ItemAccountBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemAccountViewModel) {
      itemLayoutBinding.itemViewModels = itemViewModel
    }
  }
}