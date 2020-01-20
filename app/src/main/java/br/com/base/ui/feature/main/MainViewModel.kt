package br.com.base.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.base.data.Repository
import br.com.base.data.model.Model
import br.com.base.data.model.RequestState
import br.com.base.ui.feature.main.paging.MainDataSourceFactory
import br.com.base.ui.feature.main.paging.MainPagedKeyDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val repository: Repository) : ViewModel() {
    val disposables = CompositeDisposable()

    private lateinit var lDataSource: MainPagedKeyDataSource
    private lateinit var lDataSourceFactory: MainDataSourceFactory
    private lateinit var lPagedList: LiveData<PagedList<Model>>

    private val mutableState = MutableLiveData<RequestState>()
    val state: LiveData<RequestState>
        get() = mutableState

    val pagedList: LiveData<PagedList<Model>>
        get() = if (::lPagedList.isInitialized) lPagedList else throw IllegalStateException("Call initialize function before use this variable")

    fun onCreate() {
        initDataSourceFactory()
    }

    private fun initDataSourceFactory() {
        lDataSourceFactory = MainDataSourceFactory(
            repository, disposables
        )

        initStateObserver(lDataSourceFactory)

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        lPagedList = LivePagedListBuilder(lDataSourceFactory, config).build()
    }

    private fun initStateObserver(dataSourceFactory: MainDataSourceFactory) {

        val disposable = dataSourceFactory.dataSource
            .doOnNext { source -> lDataSource = source }
            .flatMap { source -> source.state }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { current -> mutableState.value = current }
        disposables.add(disposable)
    }
}
