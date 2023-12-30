/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package api

import common.Constant
import entidades.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object API {

    interface API {

        @GET("platforms/lists/parents")
        fun getPlatforms(
            @Query("ordering") ordering: String,
            @Query("key") apiKey: String
        ): Call<RawgData<List<ParentPlatform>>>

        @GET("platforms/{id}")
        fun getPlatform(
            @Path("id") id: Int,
            @Query("key") apiKey: String
        ): Call<ParentPlatform.Platform>

        @GET("games")
        fun getGames(
            @Query("key") apiKey: String,
            @Query("platforms") platform: Int,
            @Query("page") page: Int,
            @Query("ordering") ordering: String
        ): Call<RawgData<List<Game>>>

        @GET("games")
        fun searchGames(
            @Query("key") apiKey: String,
            @Query("search") search: String,
            @Query("search_precise") searchPrecise: Boolean,
            @Query("search_exact") searchExact: Boolean
        ): Call<RawgData<List<Game>>>

        @GET("games/{id}")
        fun getGameDetails(
            @Path("id") id: Long,
            @Query("key") apiKey: String
        ): Call<Game>

        @GET("games/{id}/screenshots")
        fun getGameScreenshots(
            @Path("id") id: Long,
            @Query("key") apiKey: String
        ): Call<RawgData<List<ScreenShot>>>


    }

    private fun getRetroFit(): API {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        return retrofit.create(API::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
    }

    fun loadPlatforms(success: (platformList: RawgData<List<ParentPlatform>>  ) -> Unit, failure: () -> Unit) {

        getRetroFit().getPlatforms("name",Constant.API_KEY).enqueue(object: Callback<RawgData<List<ParentPlatform>>> {
            override fun onResponse(call: Call<RawgData<List<ParentPlatform>>>, response: Response<RawgData<List<ParentPlatform>>>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - loadPlatforms")
                }
            }
            override fun onFailure(call: Call<RawgData<List<ParentPlatform>>>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }


    fun loadGamesPlatform(platformId : Int, success: (platform: ParentPlatform.Platform) -> Unit, failure: () -> Unit) {

        if(platformId <= 0){
            return
        }

        getRetroFit().getPlatform(platformId, Constant.API_KEY).enqueue(object: Callback<ParentPlatform.Platform> {
            override fun onResponse(call: Call<ParentPlatform.Platform>, response: Response<ParentPlatform.Platform>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - loadGamesPlatform")
                }
            }
            override fun onFailure(call: Call<ParentPlatform.Platform>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }


    fun loadGames(platformId : Int, page: Int, success: (listGames: RawgData<List<Game>>) -> Unit, failure: () -> Unit) {

        if(platformId <= 0 || page <= 0){
            return
        }

        getRetroFit().getGames(Constant.API_KEY, platformId, page,"name").enqueue(object: Callback<RawgData<List<Game>>> {
            override fun onResponse(call: Call<RawgData<List<Game>>>, response: Response<RawgData<List<Game>>>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - loadGames")
                }
            }
            override fun onFailure(call: Call<RawgData<List<Game>>>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }


    fun searchGames(query: String, searchPrecise: Boolean, searchExact: Boolean, success: (listGames: RawgData<List<Game>>) -> Unit, failure: () -> Unit) {

        if(query.isEmpty()){
            return
        }

        getRetroFit().searchGames(Constant.API_KEY, query,searchPrecise,searchExact).enqueue(object: Callback<RawgData<List<Game>>> {
            override fun onResponse(call: Call<RawgData<List<Game>>>, response: Response<RawgData<List<Game>>>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - searchGames")
                }
            }
            override fun onFailure(call: Call<RawgData<List<Game>>>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }

    fun loadGameDetails(gameId: Long, success: (gameDetails: Game) -> Unit, failure: () -> Unit) {

        if(gameId <= 0){
            return
        }

        getRetroFit().getGameDetails(gameId, Constant.API_KEY).enqueue(object: Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - loadGameDetails")
                }
            }
            override fun onFailure(call: Call<Game>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }


    fun loadGameScreenshots(gameId: Long, success: (gameScreenshots: RawgData<List<ScreenShot>>) -> Unit, failure: () -> Unit) {

        if(gameId <= 0){
            return
        }

        getRetroFit().getGameScreenshots(gameId, Constant.API_KEY).enqueue(object: Callback<RawgData<List<ScreenShot>>> {
            override fun onResponse(call: Call<RawgData<List<ScreenShot>>>, response: Response<RawgData<List<ScreenShot>>>) {
                if(response.isSuccessful){
                    println(response)
                    success((response.body()!!))
                }else{
                    println(response.errorBody())
                    println("ERROR - loadGameScreenshots")
                }
            }
            override fun onFailure(call: Call<RawgData<List<ScreenShot>>>, t: Throwable) {
                println(t.message)
                failure()
            }
        })
    }

}