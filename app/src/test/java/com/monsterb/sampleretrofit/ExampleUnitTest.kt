package com.monsterb.sampleretrofit

import android.util.Log
import com.monsterb.sampleretrofit.net.API
import com.monsterb.sampleretrofit.net.APIResult
import com.monsterb.sampleretrofit.net.JSONDATA
import com.monsterb.sampleretrofit.net.RetrofitBuilder
import okhttp3.MultipartBody
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun apiTest() {
//        val jsonData = JSONDATA()
//        jsonData.data = "adfdff"
//        jsonData.age = 10
//        jsonData.name = "kkk"

        val formData = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("age", "10")
            .addFormDataPart("data", "1020")
            .addFormDataPart("name", "adfad")
            .build()

        val age = 10
        val name = "kkk"
        val call: Call<APIResult> =
            RetrofitBuilder.getInstance()!!.create(API::class.java).sendMsgWithHeader("application/x-www-form-urlencoded", age, name)

        try {
            val response = call.execute()
            val responseData: APIResult? = response.body()

            responseData?.let { responseD ->
                responseD.form?.let { form ->
                    form.name?.let { it ->
                        assertEquals("", it)
                    }

                }
            }

        } catch (exception: Exception) {
            print(exception.message)
        }



    }
}