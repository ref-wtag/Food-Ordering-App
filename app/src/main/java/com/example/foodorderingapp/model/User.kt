package com.example.foodorderingapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User: RealmObject() {
    @PrimaryKey
    var id: String = ""
    var userName: String = ""
    var userEmail: String = ""
    var userPassword: String = ""
    var userType: String = "" // "admin" or "customer
}