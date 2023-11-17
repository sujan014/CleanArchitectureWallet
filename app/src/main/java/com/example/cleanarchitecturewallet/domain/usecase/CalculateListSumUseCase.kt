package com.example.cleanarchitecturewallet.domain.usecase

class CalculateListSumUseCase {

    operator fun invoke(list: List<Float>): Float{
        var result = 0f
        for(items in list){
            result += items
        }
        return result
    }
}