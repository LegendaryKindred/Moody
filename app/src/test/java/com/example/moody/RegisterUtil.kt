package com.example.moody

object RegisterUtil {

    // private val existingUser = listOf()
    fun validateRegisterInput(

        firstName: String,
        lastname: String,
        username: String,
        email: String,
        password: String,
        phone:String

        ):Boolean{
        if (firstName.isEmpty() || lastname.isEmpty() ||
            username.isEmpty() || email.isEmpty()||
            password.isEmpty() || phone.isEmpty() ){
            return false
        }
        if (password.length < 8){
            return false
        }
        if (phone.length != 10){
            return false
        }
        return true
    }

}