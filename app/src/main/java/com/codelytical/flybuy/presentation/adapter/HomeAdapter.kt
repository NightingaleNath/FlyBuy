package com.codelytical.flybuy.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import com.codelytical.flybuy.databinding.SingleItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    private var onItemClickListener : ((ProductResponse)-> Unit) = {}

    fun setOnItemClickListener(listener : (ProductResponse)-> Unit){
        onItemClickListener = listener
    }

    inner class HomeViewHolder(private val binding : SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(productResponse: ProductResponse){

            Glide.with(binding.itemImage)
                .load(productResponse.image)
                .into(binding.itemImage)

            binding.itemTitle.text = productResponse.name
            binding.itemPrice.text = "GHc ${productResponse.price}"
            binding.itemRating.text = "${productResponse.rating.rate!!.toDouble()}"
            binding.itemReview.text = "${productResponse.rating.count!!.toInt()} Reviews"
            binding.itemQuantity.text = "Quantity: ${productResponse.quantity}"

            binding.itemView.setOnClickListener {
                onItemClickListener(productResponse)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() =  differ.currentList.size



}