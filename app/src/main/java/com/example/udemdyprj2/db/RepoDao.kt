package com.example.udemdyprj2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import kotlin.collections.ArrayList

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    fun fetchAllRepos(): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRepos(repos:List<Repo>)
}