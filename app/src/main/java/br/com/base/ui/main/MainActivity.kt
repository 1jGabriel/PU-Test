package br.com.base.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import br.com.base.R
import br.com.base.data.model.Model
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private val adapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupRecyclerView()

        viewModel.onCreate()

        viewModel.pagedList.observe(this, Observer<PagedList<Model>> {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposables.clear()
    }
}
