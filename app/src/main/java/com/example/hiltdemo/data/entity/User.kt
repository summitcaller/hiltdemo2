package com.example.hiltdemo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String? = "",
    @ColumnInfo(name = "last_name") val lastName: String? = ""
) {
    override fun toString(): String = "User(uid=$uid, firstName=$firstName, lastName=$lastName)"
}


@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["user_id"])])
data class Task(
    @PrimaryKey val taskid:Int,
    @ColumnInfo(name = "user_id") val userId:Int,
    @ColumnInfo(name = "description") val description:String?
)