package com.example.moody


import org.junit.Test
import com.google.common.truth.Truth.assertThat

/**
 * Login test cases
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class LoginUtilTest {

    @Test
    fun `return false if everything is empty`(){
        val result = LoginUtil.validateLoginInput(
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `return false if username is empty`(){
        val result = LoginUtil.validateLoginInput(
            "",
            "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `return false if password is empty`(){
        val result = LoginUtil.validateLoginInput(
            "asdf",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `return false if length of password is less than 8`(){
        val result = LoginUtil.validateLoginInput(
            "asdf",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `return false if invalid character entered`(){
        val result = LoginUtil.validateLoginInput(
            "测试",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `return ture if everything is valid`(){
        val result = LoginUtil.validateLoginInput(
            "asdf",
            "12345678"
        )
        assertThat(result).isTrue()
    }
}