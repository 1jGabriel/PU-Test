package br.com.base.data.model

data class ResponseApi(
    val code: String,
    val response: ArrayList<Model>
)

data class Model(
    val deal_id: String
)
