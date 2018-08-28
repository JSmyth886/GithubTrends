package com.jsmyth.githubtrends.ui.list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsmyth.githubtrends.MainActivity
import com.jsmyth.githubtrends.R
import com.jsmyth.githubtrends.common.BaseFragment
import com.jsmyth.githubtrends.databinding.ListFragmentBinding
import com.jsmyth.githubtrends.ui.detail.DetailFragment

class ListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: ListFragmentAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    init {
        title = "Github Trends"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        binding.viewModel = viewModel

        adapter = ListFragmentAdapter()
        linearLayoutManager = LinearLayoutManager(this.context)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = linearLayoutManager

        viewModel.navigateToDetails.observe(this, Observer(::navigateToDetail))
        viewModel.repositoryList.observe(this, Observer(::repositoryListUpdated))
    }

    private fun navigateToDetail(item: ListItemViewModel?) {
        if (item != null) {
            (activity as MainActivity).navigate(DetailFragment.newInstance(), true)
            viewModel.navigateToDetails.value = null
        }
    }

    private fun repositoryListUpdated(list: MutableList<ListItemViewModel>?) {
        if (list != null) {
            adapter.updateRepos(list)
        }
    }
}
