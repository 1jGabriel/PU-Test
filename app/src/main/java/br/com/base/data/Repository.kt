package br.com.base.data

import br.com.base.data.model.Model
import io.reactivex.Single

interface Repository {
    fun getData(): Single<ArrayList<Model>>
}
