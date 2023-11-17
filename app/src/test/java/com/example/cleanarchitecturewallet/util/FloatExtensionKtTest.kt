package com.example.cleanarchitecturewallet.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

class FloatExtensionKtTest{

    @Test
    fun testFloatGermanFormatWithoutCommaValue(){
        val number = 10f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ 10")
    }

    @Test
    fun testFloatEnglishFormatWithoutCommaValue(){
        val number = 10f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ 10")
    }

    @Test
    fun testFloatEnglishFormatWithOneCommaValue(){
        val number = 10.9f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ 10.90")
    }

    @Test
    fun testFloatEnglishFormatWithTwoCommaValue(){
        val number = 10.36f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ 10.36")
    }

    @Test
    fun testFloatIsZeroFormatToSingleZero(){
        val number = 0f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ 0")
    }

    @Test
    fun testFloatIsZeroWithTwoCommaValuesThereIsNoCommaValueButItsForcedTo(){
        val number = 0f
        val result = number.formatToPrice(Locale.ENGLISH, alwaysUseTwoCommaDigits = true)
        assertThat(result).isEqualTo("$ 0.00")
    }

    @Test
    fun TestNegativeFloatWithCommaValueIsFormattedCorrectly(){
        val number = -1.20f
        val result = number.formatToPrice(Locale.ENGLISH, alwaysUseTwoCommaDigits = true)
        assertThat(result).isEqualTo("$ -1.20")
    }

    @Test
    fun TestNegativeFloatWithoutCommaValueIsFormattedCorrectly(){
        val number = -40f
        val result = number.formatToPrice(Locale.ENGLISH)
        assertThat(result).isEqualTo("$ -40")
    }

    @Test
    fun TestNegativeFloatWithoutCommaValueAndForcedToTwoCommasIsFormattedCorrectly(){
        val number = -40f
        val result = number.formatToPrice(Locale.ENGLISH, alwaysUseTwoCommaDigits = true)
        assertThat(result).isEqualTo("$ -40.00")
    }
}