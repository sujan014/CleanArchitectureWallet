package com.example.cleanarchitecturewallet.domain.usecase

import java.text.DecimalFormatSymbols

/*
to write test case
-> right click on class
-> generate
-> testing library JUnit4 -> OK
-> UNCHECK -> Show only existent source roots -> OK

    In country like Germany, for decimal separator, "," is used
 */
class ParseTransactionValueInputUseCase {

    operator fun invoke(value: String): Boolean{
        val decimalPointFormat = DecimalFormatSymbols.getInstance().decimalSeparator
        /*
        0+ -> one or more zeros
        $decimalPointFormat? -> decimal optional
        [0]{0,2} -> 0  0 upto 2 times
         */

        val regexForNull = "0+$decimalPointFormat?[0]{0,2}"
        if (value.matches(Regex(regexForNull))){    //"0.0" "0.00" "0000.0"
            return false
        }
        /*
        [0-9] -> possibility of all values 0-9
        [0-9]* -> append values 0 or infinity times
        [0-9]{1,2} -> values [0-9] 1 or 2 times
         */
        val regexGeneral = "[0-9]*$decimalPointFormat?[0-9]{1,2}"
        return value.matches(Regex(regexGeneral))
    }
    // test this with test cases
}