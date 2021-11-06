package com.example.moody

object LoginUtil {

    fun validateLoginInput(
        username: String,
        password: String
    ):Boolean{
        if (username.isEmpty() || password.isEmpty()){
            return false
        }
        if (password.length < 8){
            return false
        }
        return true
    }
}