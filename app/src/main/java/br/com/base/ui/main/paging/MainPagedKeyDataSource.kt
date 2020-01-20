package br.com.base.ui.main.paging

import androidx.paging.PositionalDataSource
import br.com.base.data.Repository
import br.com.base.data.model.Model
import br.com.base.ui.main.RequestState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class MainPagedKeyDataSource(
    private val repository: Repository,
    private val disposable: CompositeDisposable
) : PositionalDataSource<Model>() {

    val state: Subject<RequestState> = PublishSubject.create()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Model>) {
        disposable.add(
            repository.getData()
                .subscribe({
                    callback.onResult(it)
                }, {
                    state.onNext(RequestState.Error(it))
                })
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Model>) {
        disposable.add(
            repository.getData()
                .subscribe({
                    callback.onResult(it, params.requestedStartPosition, params.pageSize)
                }, {
                    state.onNext(RequestState.Error(it))
                })
        )
    }
}
