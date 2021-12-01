package com.monsterb.sampleretrofit.net

import com.monsterb.sampleretrofit.net.RetrofitBuilder.SERVER_KEY
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    //json
    @Headers("Content-Type: application/json",
        "Authorization: $SERVER_KEY")
    @POST("post")
    fun sendMsg(@Body json:JSONDATA): Call<APIResult>


    //다른 방식의 header 타입
    //form data
    @POST("post")
    @FormUrlEncoded
    fun sendMsgWithHeaderFormData(@Header("Content-type") type:String, @Field("age")age:Int, @Field("name")name:String ): Call<APIResult>

    //form data
    @POST("post")
    fun sendMsgWithHeaderJsonData(@Header("Content-type") type:String, @Body json: JSONDATA ): Call<APIResult>


    @GET("App/json/{page}.json")
    fun getList(
        @Path("page") page: String
    ): Call<AcommodationListResponse>

}

data class AcommodationListResponse(val msg: String, val code: String , val data: AcommodationPageInfo)

data class AcommodationPageInfo(val totalCount: Int, val product: ArrayList<AcommodationInfo>)

data class AcommodationInfo(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val description: AcommodationDesc,
    val rate: Float
)

data class AcommodationDesc (val imagePath:String, val subject:String, val price : Long)