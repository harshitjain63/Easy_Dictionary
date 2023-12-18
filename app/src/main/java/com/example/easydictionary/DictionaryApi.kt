package com.example.easydictionary

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("en/{word}") // path variable {}
    suspend fun getMeaning(@Path("word") word: String) : Response<List<WordResult>> // this is our main api call where we call the word  and this method will return a list of word result
}