package com.example.udemdyprj2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udemdyprj2.db.Repo
import kotlinx.android.synthetic.main.item_lay_row.view.*

class MyRecylerAdapter : RecyclerView.Adapter<MyRecylerAdapter.RepoModelHolder>() {

    val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoModelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lay_row, parent, false)
        return RepoModelHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RepoModelHolder, position: Int) {
        data.get(position).mlanguage?.let {
            holder.repoLang.text = data.get(position).mlanguage
        } ?: run {
            holder.repoLang.text = "NO LANG"
        }

        data.get(position).description?.let {
            holder.repoLang.text =
                holder.repoLang.text.toString().plus("\n").plus(data.get(position).description)
        } ?: kotlin.run {
            holder.repoLang.text = holder.repoLang.text.toString().plus("\n").plus("NO DESC")
        }
        holder.repoName.text = data.get(position).repoName
        holder.repoStarGazerCount.text = data.get(position).stargazerscount.toString()
    }


    fun addRepos(repos: ArrayList<Repo>) {
        data.addAll(repos)
        notifyDataSetChanged()
    }

    class RepoModelHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoName = view.repoName
        val repoLang = view.repoLang
        val repoStarGazerCount = view.repoStarGazerCount
    }

}