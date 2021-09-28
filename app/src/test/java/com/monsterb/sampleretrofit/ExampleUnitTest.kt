package com.monsterb.sampleretrofit

import com.monsterb.sampleretrofit.net.API
import com.monsterb.sampleretrofit.net.APIResult
import com.monsterb.sampleretrofit.net.JSONDATA
import com.monsterb.sampleretrofit.net.RetrofitBuilder
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Call


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun apiTestWithFormData() {

        val age = 10
        val name = "kkk"
        val call: Call<APIResult> =
            RetrofitBuilder.getInstance()!!.create(API::class.java)
                .sendMsgWithHeaderFormData("application/x-www-form-urlencoded", age, name)

        try {
            val response = call.execute()
            val responseData: APIResult? = response.body()

            responseData?.let { responseD ->
                responseD.form?.let { form ->
                    form.name?.let { it ->
                        assertEquals("kkk", it)
                    }

                }
            }

        } catch (exception: Exception) {
            print(exception.message)
        }


    }


    @Test
    fun apiTestWithJson() {
        val jsonData = JSONDATA()
        jsonData.data = "adfdff"
        jsonData.age = 10
        jsonData.name = "kkk"


        val call: Call<APIResult> =
            RetrofitBuilder.getInstance()!!.create(API::class.java)
                .sendMsgWithHeaderJsonData("application/json", jsonData)

        val response = call.execute()
        val responseData: APIResult? = response.body()



        responseData?.let { responseD ->

            responseD.json?.let { form ->

                assertEquals("kkk", form.name)

            }



            responseD.form?.let { form ->

                assertEquals("kkk", form.name)

            }

        }

        // You make fail
        //org.junit.Assert.fail("message")


    }
}