package com.example.cleanarchitecturewallet.domain.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class CalculateListSumUseCaseTest{

    private var calculateListSumUseCase = CalculateListSumUseCase()

    /*
        this will always be called before test case
        Used for initialization like Viewmodel
     */
    @Before
    fun Setup(){
        calculateListSumUseCase = CalculateListSumUseCase()
    }

    @After
    fun AfterFunction(){
        // used for cleanup
    }

    @Test
    fun testResultWithEmptyListIsNull(){
        val result = calculateListSumUseCase(emptyList())
        assertThat(result).isEqualTo(0f)
    }

    @Test
    fun testResultWithOneListItem(){
        val item = 10.5f
        val result = calculateListSumUseCase(listOf(item))
        assertThat(result).isEqualTo(item)
    }

    @Test
    fun testResultWithMultipleListItem(){
        val item1 = 10.5f
        val item2 = 10f
        val item3 = 20f
        val result = calculateListSumUseCase(listOf(item1, item2, item3))
        assertThat(result).isEqualTo(item1 + item2 + item3)
    }

    @Test
    fun testResultWithMultipleListItemWithOneItemNEgative(){
        val item1 = 10.5f
        val item2 = -10f
        val item3 = 20f
        val result = calculateListSumUseCase(listOf(item1, item2, item3))
        assertThat(result).isEqualTo(item1 + item2 + item3)
    }
}