package com.example.udemdyprj2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repo")
data class Repo (@PrimaryKey
                 @ColumnInfo(name = "id")
                 @SerializedName("id")val id:String,
                 @ColumnInfo(name = "name")
                 @SerializedName("name")val repoName:String?,
                 @ColumnInfo(name = "description")
                 @SerializedName("description") val description:String?,
                 @ColumnInfo(name = "language")
                 @SerializedName("language") val mlanguage:String?,
                 @ColumnInfo(name = "stargazers_count")
                 @SerializedName("stargazers_count") val stargazerscount:Int)
