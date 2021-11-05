package com.example.moody

import org.junit.Test
import com.google.common.truth.Truth.assertThat


/**
 * Register test cases
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RegisterUtilTest {
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
        assertThat(result).isFalse()
    }

    @Test
    fun `return false if phone number is not 10 digits`(){
        val result = RegisterUtil.validateRegisterInput(
            "asd",
            "f",
            "asdf",
            "asdf@asdf.com",
            "12345678",
            "11111"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `return false if password is less than 8 digits`(){
        val result = RegisterUtil.validateRegisterInput(
            "asd",
            "f",
            "asdf",
            "asdf@asdf.com",
            "12345678",
            "11111"
        )
        assertThat(result).isFalse()
    }
}