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
    fun sendMsgWithHeader(@Header("Content-type") type:String, @Field("age")age:Int, @Field("name")name:String ): Call<APIResult>
}