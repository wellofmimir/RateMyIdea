package com.molokosoft.ratemyidea.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import com.molokosoft.ratemyidea.network.model.Idea

class Database(context: Context) {

    companion object {
        private const val DATABASE_NAME = "idea_database.sqlite"
        private const val DATABASE_VERSION = 1
    }

    private val database: SQLiteDatabase = context.openOrCreateDatabase (
        DATABASE_NAME,
        Context.MODE_PRIVATE,
        null
    )

    private val databasePath = context.getDatabasePath(DATABASE_NAME)

    init {
        database.execSQL("PRAGMA foreign_keys = ON;")
        database.execSQL("CREATE TABLE IF NOT EXISTS databaseVersion (version INTEGER PRIMARY KEY)".trimIndent())
        database.execSQL("CREATE TABLE IF NOT EXISTS ideas (uuid TEXT NOT NULL PRIMARY KEY, title TEXT NOT NULL, description TEXT NOT NULL, total INTEGER NOT NULL DEFAULT 0, likes INTEGER NOT NULL DEFAULT 0, dislikes INTEGER NOT NULL DEFAULT 0)".trimIndent())
    }

    fun insertIdea (
        idea: Idea
    ) {
        val values = ContentValues().apply {
            put("uuid", idea.uuid)
            put("title", idea.title)
            put("description", idea.description)
            put("total", idea.total)
            put("likes", idea.likes)
            put("dislikes", idea.dislikes)
        }

        database.insert("ideas", null, values)
    }

    fun getAllIdeas(): List<Idea> {
        val list = mutableListOf<Idea>()
        val cursor = database.rawQuery("SELECT * FROM ideas", null)

        while (cursor.moveToNext()) {
            list.add (
                Idea (
                    uuid = cursor.getString(cursor.getColumnIndexOrThrow("uuid")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    total = cursor.getInt(cursor.getColumnIndexOrThrow("total")),
                    likes = cursor.getInt(cursor.getColumnIndexOrThrow("likes")),
                    dislikes = cursor.getInt(cursor.getColumnIndexOrThrow("dislikes"))
                )
            )
        }

        cursor.close()
        return list
    }
}