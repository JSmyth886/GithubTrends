package com.jsmyth.githubtrends.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jsmyth.githubtrends.BR
import com.jsmyth.githubtrends.R

class ListFragmentAdapter : RecyclerView.Adapter<ListFragmentAdapter.ViewHolder>() {

    private var repos: MutableList<ListItemViewModel> = ArrayList()

    override fun getItemCount(): Int {
        return repos.count()
    }

    fun updateRepos(newList: List<ListItemViewModel>) {
        repos.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                R.layout.list_item_view, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItemViewModel) {
            binding.setVariable(BR.viewModel, item)
            binding.executePendingBindings()
        }
    }
}
