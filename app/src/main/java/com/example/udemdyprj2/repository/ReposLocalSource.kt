package com.example.udemdyprj2.repository

import com.example.udemdyprj2.RxApp
import com.example.udemdyprj2.db.AppDatabase
import com.example.udemdyprj2.db.Repo
import io.reactivex.Observable

object ReposLocalSource:RepoDataSource {
    override fun fetchRepos(userName: String): Observable<List<Repo>> {
        return Observable.fromCallable {
            AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.fetchAllRepos()
        }
    }

    override fun saveRepos(repos: List<Repo>) {
        AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.saveAllRepos(repos)
    }
}