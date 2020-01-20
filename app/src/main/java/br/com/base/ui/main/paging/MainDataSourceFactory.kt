package br.com.base.ui.main.paging

import androidx.paging.DataSource
import br.com.base.data.Repository
import br.com.base.data.model.Model
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class MainDataSourceFactory(
    private val repository: Repository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Model>() {

    val dataSource: Subject<MainPagedKeyDataSource> = PublishSubject.create()

    override fun create(): DataSource<Int, Model> {
        val mDataSource = MainPagedKeyDataSource(repository, compositeDisposable)

        dataSource.onNext(mDataSource)

        return mDataSource
    }
}
