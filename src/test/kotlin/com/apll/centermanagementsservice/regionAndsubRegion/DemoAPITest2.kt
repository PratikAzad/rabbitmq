package com.apll.centermanagementsservice.regionAndsubRegion


import org.junit.Test
import org.assertj.core.api.Assertions.assertThat


class DemoAPITest2 {

    @Test
    fun shouldRepeat() {
        val string = "foo bar "
        val result = string.repeat(2)
        assertThat(result).isEqualTo(string + string)
    }


    @Test
    fun shouldRepeatEmpty() {
        val string = ""
        val result = string.repeat(Integer.MAX_VALUE)
        assertThat(result).isEqualTo("")
    }


    @Test
    fun shouldRepeatZeroTimes() {
        val string = "foo"
        val result = string.repeat(0)
        assertThat(result).isEqualTo("")
    }


    @Test
    fun shouldCheckIfBlank() {
        assertThat(" ".isBlank()).isTrue()
    }

}


