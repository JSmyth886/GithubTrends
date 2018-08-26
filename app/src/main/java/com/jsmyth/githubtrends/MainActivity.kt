package com.jsmyth.githubtrends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jsmyth.githubtrends.common.BaseFragment
import com.jsmyth.githubtrends.databinding.MainActivityBinding
import com.jsmyth.githubtrends.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.viewModel = viewModel

        if (savedInstanceState == null) {
            navigate(ListFragment.newInstance())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.backPressed()
    }

    fun navigate(fragment: Fragment, withBackStack: Boolean = false) {
        if (withBackStack)
            navigateToWithBackStack(R.id.container, fragment)
        else
            navigateTo(R.id.container, fragment)

        viewModel.updateTitle((fragment as BaseFragment).title)
    }

    private fun navigateToWithBackStack(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(container, fragment)
                .addToBackStack(null)
                .commit()
    }

    private fun navigateTo(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(container, fragment)
                .commitNow()
    }
}
