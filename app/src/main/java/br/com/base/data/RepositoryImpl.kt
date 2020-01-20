package br.com.base.data

import br.com.base.data.model.Model
import io.reactivex.Single

class RepositoryImpl(val service: Service) : Repository {
    override fun getData(): Single<ArrayList<Model>> {
        return service.getCharacters()
            .map { response ->
                when {
                    response.isSuccessful -> {
                        response.body()?.response ?: arrayListOf()
                    }
                    else -> throw Throwable(response.message())
                }
            }
    }
}
