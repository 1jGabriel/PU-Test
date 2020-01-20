package br.com.base.data.model

data class ResponseApi(
    val code: String,
    val response: ArrayList<Model>
)

data class Model(
    val deal_id: String,
    val images: ArrayList<ImageDeal>,
    val short_title: String,
    val sale_price: String,
    val partner: Partner
)

data class Partner(val name: String)

data class ImageDeal(val image: String)
