package com.example.mylogin5b

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperUsuario(context: Context) : SQLiteOpenHelper(context, DB_name, null, DB_version){
    companion object{
        private val DB_version = 1
        private val DB_name = "dbServicios"
        private val nomTable = "usuarios"
        private val keyId = "id"
        private val usrLogin = "userLogin"
        private val usrPass = "userPass"
        private val usrEmail = "userEmail"
        private val usrNombre = "userNombre"
    }

    val sqlCreate: String = "CREATE TABLE $nomTable ($keyId INTEGER PRIMARY KEY, $usrLogin TEXT, $usrPass TEXT,$usrEmail TEXT, $usrNombre TEXT)"
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(sqlCreate)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $nomTable")
        }
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db,oldVersion,newVersion)
    }
}

