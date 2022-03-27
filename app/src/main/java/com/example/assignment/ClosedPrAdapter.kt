package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.assignment.databinding.PrItemBinding

class ClosedPrAdapter(private var list: List<PrDataResponse> = emptyList()): RecyclerView.Adapter<ClosedPrAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pr_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding: PrItemBinding = PrItemBinding.bind(itemView)

        fun bind(data: PrDataResponse) {
            with(binding) {
                ivLogo.load(data.user?.avatarUrl)
                tvTitle.text = data.title ?: ""
                tvUserName.text = data.user?.login ?: ""
                tvCreated.text = dateFormat(data.createdAt ?: "")
                tvClosed.text = dateFormat(data.closedAt ?: "")
            }
        }
    }

    fun updateList(list: List<PrDataResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun dateFormat(date: String): String {
        return date.substring(0, 10)
    }

}