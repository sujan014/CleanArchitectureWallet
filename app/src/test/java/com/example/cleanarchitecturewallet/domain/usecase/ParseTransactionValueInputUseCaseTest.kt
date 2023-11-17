package com.example.cleanarchitecturewallet.domain.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ParseTransactionValueInputUseCaseTest {
    private val transactionValueInputUseCase = ParseTransactionValueInputUseCase()

    @Test
    fun testZeroWithoutCommaReturnsFalse(){
        val result = transactionValueInputUseCase("0")
        assertThat(result).isFalse()
    }

    @Test
    fun testZeroWithCommaReturnsFalse(){
        val result = transactionValueInputUseCase("0,00")
        assertThat(result).isFalse()
    }

    @Test
    fun testMultipleZeroesWithCommaReturnsFalse(){
        val result = transactionValueInputUseCase("0000,00")
        assertThat(result).isFalse()
    }

    @Test
    fun testZeroBeforeCommaAndValueAfterCommaReturnsTrue(){
        val result = transactionValueInputUseCase("0.12")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalSingleValueWithoutCommaReturnsTrue(){
        val result = transactionValueInputUseCase("4")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalSingleValueWithOneCommaValueAfterReturnsTrue(){
        val result = transactionValueInputUseCase("4.5")
        assertThat(result).isTrue()
    }

    @Test
    fun testNormalValueWithTwoCommaValueAfterReturnsTrue(){
        val result = transactionValueInputUseCase("4357.43")
        assertThat(result).isTrue()
    }

    @Test
    fun testTooLongValueReturnsFalse(){
        val result = transactionValueInputUseCase("4.5657")
        assertThat(result).isFalse()
    }

    @Test
    fun testNoValueReturnsFalse(){
        val result = transactionValueInputUseCase("12343f")
        assertThat(result).isFalse()
    }

    @Test
    fun testLeadingZeroesValueReturnsTrue(){
        val result = transactionValueInputUseCase("04.56")
        assertThat(result).isTrue()
    }
}