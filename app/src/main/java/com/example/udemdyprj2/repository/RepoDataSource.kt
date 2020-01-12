package com.example.udemdyprj2.repository

import com.example.udemdyprj2.db.Repo
import io.reactivex.Observable

interface RepoDataSource {
    fun fetchRepos(userName:String):Observable<List<Repo>>
    fun saveRepos(repos:List<Repo>)
}