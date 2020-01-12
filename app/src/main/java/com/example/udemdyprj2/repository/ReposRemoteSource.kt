package com.example.udemdyprj2.repository

import com.example.udemdyprj2.db.Repo
import com.example.udemdyprj2.network.GithubApiClient
import io.reactivex.Observable

object ReposRemoteSource:RepoDataSource {
    override fun fetchRepos(userName: String): Observable<List<Repo>> {
        return GithubApiClient.githubService.getRepos(userName)
    }

    override fun saveRepos(repos: List<Repo>) {
    }
}