package com.example.moody

import com.google.common.truth.Truth
import org.junit.Assert
import org.junit.Test

class RegisterFragmentTest {
    @Test
    fun testEmptyRegister() {
        Assert.assertEquals(
            "editFirstName.getText().toString().trim()",
            "a",
            "b"
        )
    }
    @Test
    fun `return false if anything is empty`(){
        val result = RegisterUtil.validateRegisterInput(
            "",
            "",
            "",
            "",
            "",
            ""
        )
        Assert.assertEquals(result,false)
    }

    @Test
    fun `return false if phone number is not 10 digits`(){
        val result = RegisterUtil.validateRegisterInput(
            "asd",
            "f",
            "asdf",
            "asdf@asdf.com",
            "123",
            "11111"
        )
        Assert.assertEquals(result,false)
    }
}