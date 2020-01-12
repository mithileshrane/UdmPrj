package com.example.udemdyprj2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemdyprj2.db.Repo
import com.example.udemdyprj2.network.GithubApiClient
import com.example.udemdyprj2.repository.RepoRepository
import com.example.udemdyprj2.repository.ReposLocalSource
import com.example.udemdyprj2.repository.ReposRemoteSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel: ViewModel() {

    private val repoLiveData= MutableLiveData<List<Repo>>()

    private val compositeDisposable=CompositeDisposable()

    val reposiroty=RepoRepository(ReposRemoteSource,ReposLocalSource)


    fun getMyRepos(userName:String){
        //GithubApiClient.getGithubServiceInst().getRepos("mithileshrane")
      /*val repodisposable=  GithubApiClient.getGithubServiceInst().getRepos(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    it->repoLiveData.value=it
            },{
                    it->
                println(it.message)
                println(it.stackTrace)
            },{
                println("Complete")
            })
*/

        val repodisposable=  reposiroty.fetchRepos(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    it->repoLiveData.value=it
            },{
                    it->
                println(it.message)
                println(it.stackTrace)
            },{
                println("Complete")
            })

        compositeDisposable.add(repodisposable)

    }

    fun getLiveData():LiveData<List<Repo>> = repoLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}