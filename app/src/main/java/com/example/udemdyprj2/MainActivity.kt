package com.example.udemdyprj2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udemdyprj2.db.Repo
import com.example.udemdyprj2.network.GithubApiClient
import com.example.udemdyprj2.viewmodel.RepoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dataList = ArrayList<Repo>()
    private lateinit var repoAdapter: MyRecylerAdapter

    private lateinit var repoViewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutMngr = LinearLayoutManager(applicationContext)
        val divder=DividerItemDecoration(recylerView.context,DividerItemDecoration.VERTICAL)
        recylerView.layoutManager = linearLayoutMngr
        repoAdapter = MyRecylerAdapter()
        recylerView.adapter = repoAdapter
        recylerView.addItemDecoration(divder)

        repoViewModel=ViewModelProviders.of(this).get(RepoViewModel::class.java)

        /*dataList.add(Repo("F0","HGG"))
        dataList.add(Repo("F0","HGG"))
        dataList.add(Repo("F0","HGG"))
        repoAdapter.addRepos(dataList)*/

       // getRepos()

        getReposData(repoViewModel)
        observeMyRepos(repoViewModel)
    }

    private fun getReposData(repoViewModel: RepoViewModel) {
        repoViewModel.getMyRepos("mrabelwahed")

    }

    private fun observeMyRepos(repoViewModel: RepoViewModel){
        repoViewModel.getLiveData().observe(this, Observer {
            repoAdapter.addRepos(it as ArrayList<Repo>)
        })
    }

    fun getRepos() {
        //GithubApiClient.getGithubServiceInst().getRepos("mithileshrane")
        GithubApiClient.getGithubServiceInst().getRepos("mrabelwahed")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it->repoAdapter.addRepos(it as ArrayList<Repo>)
            },{
                it->
                println(it.message)
                println(it.stackTrace)
            },{
                println("Complete")
            })
    }
}
