package com.example.udemdyprj2.network

import com.example.udemdyprj2.db.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{user}/repos")
    fun getRepos(@Path(value = "user") username:String):Observable<List<Repo>>

}