package com.example.moody

object LoginUtil {

    fun validateLoginInput(
        username: String,
        password: String
    ):Boolean{
        if (username.isEmpty() || password.isEmpty()){
            return false
        }
        if (password.count() < 8){
            return false
        }
        return true
    }
}