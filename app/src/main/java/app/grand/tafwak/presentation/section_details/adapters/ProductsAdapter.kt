package app.grand.tafwak.presentation.section_details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.domain.home.models.Sections
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.utils.SingleLiveEvent
import app.grand.tafwak.presentation.home.viewModels.ItemSectionViewModel
import com.structure.base_mvvm.databinding.ItemProductBinding
import com.structure.base_mvvm.databinding.ItemSectionBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
  var clickEvent: SingleLiveEvent<Sections> = SingleLiveEvent()
  private val differCallback = object : DiffUtil.ItemCallback<Sections>() {
    override fun areItemsTheSame(oldItem: Sections, newItem: Sections): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sections, newItem: Sections): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    val itemViewModel = ItemSectionViewModel(data)
    holder.itemLayoutBinding.btnAdd.setOnClickListener {
      holder.itemLayoutBinding.btnAdd.visibility = View.GONE
      holder.itemLayoutBinding.tvCounter.visibility = View.VISIBLE
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
    lateinit var itemLayoutBinding: ItemProductBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemSectionViewModel) {
      itemLayoutBinding.itemViewModels = itemViewModel
    }
  }

}