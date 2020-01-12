package com.example.udemdyprj2.repository

import com.example.udemdyprj2.db.Repo
import io.reactivex.Observable

class RepoRepository(val repoRemoteSource:ReposRemoteSource,val repoLocalSource:ReposLocalSource):RepoDataSource {
    override fun fetchRepos(userName: String): Observable<List<Repo>> {
        return Observable.concatArray(repoLocalSource.fetchRepos(userName),repoRemoteSource.fetchRepos(userName))
            .distinct()
            .doOnNext{
                saveRepos(it)
            }
            .onErrorResumeNext(Observable.empty())
    }

    override fun saveRepos(repos: List<Repo>) {
        //Observable.fromCallable {
            repoLocalSource.saveRepos(repos)
        //}
    }

}