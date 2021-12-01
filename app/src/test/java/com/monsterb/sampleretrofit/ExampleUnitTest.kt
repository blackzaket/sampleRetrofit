package com.monsterb.sampleretrofit

import com.monsterb.sampleretrofit.net.*
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
    fun apiTestGet() {

        val age = 10
        val name = "kkk"
//        val call: AcommodationListResponse =
//            RetrofitBuilder.getInstance()!!.create(API::class.java)
//                .getList("1")

        try {
            val call: Call<AcommodationListResponse> =
                RetrofitBuilder.getInstance()!!.create(API::class.java)
                    .getList("1")
            val response =  call.execute()
            val body = response.body()
            body?.let {
                println(body.data)
            }
//
//            val response = call.execute()
//            val responseData: APIResult? = response.body()
//
//            responseData?.let { responseD ->
//                responseD.form?.let { form ->
//                    form.name?.let { it ->
//                        assertEquals("kkk", it)
//                    }
//
//                }
//            }

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