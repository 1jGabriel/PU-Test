package br.com.base.data

import br.com.base.data.model.ResponseApi
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("deals.json")
    fun getCharacters(): Single<Response<ResponseApi>>
}
