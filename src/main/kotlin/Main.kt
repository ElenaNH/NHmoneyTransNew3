fun main(args: Array<String>) {
    println("Hello!")
    println("Hello once more!!")

    println("The transfer tax for ${transformBy100(15_500_00uL)} by default card is " + calcTax(transferMoneyCents = 15_500_00uL) + " cents")
    //
    var cardType: String // "VK Pay", "Visa", "Мир", "Mastercard", "Maestro"
    var precedingMonthMoneyCents: ULong
    var transferMoneyCents: ULong
    var taxCents: ULong
    //
    cardType = "Visa"
    precedingMonthMoneyCents = 1_000_00uL
    transferMoneyCents = 4_000_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println("The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType is ${transformBy100(taxCents)}")

    cardType = "Мир"
    precedingMonthMoneyCents = 1_000_00uL
    transferMoneyCents = 4_700_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println("The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType is ${transformBy100(taxCents)}")

    cardType = "Mastercard"
    precedingMonthMoneyCents = 50_000_00uL
    transferMoneyCents = 950_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println(
        "The transfer tax for ${transformBy100(transferMoneyCents)} after ${transformBy100(precedingMonthMoneyCents)} by $cardType is ${
            transformBy100(
                taxCents
            )
        }"
    )

    cardType = "Mastercard"
    precedingMonthMoneyCents = 74_500_00uL
    transferMoneyCents = 1_000_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println(
        "The transfer tax for ${transformBy100(transferMoneyCents)} after ${transformBy100(precedingMonthMoneyCents)} by $cardType is ${
            transformBy100(
                taxCents
            )
        }"
    )

    cardType = "Maestro"
    precedingMonthMoneyCents = 75_000_00uL
    transferMoneyCents = 1_000_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println(
        "The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType after ${
            transformBy100(
                precedingMonthMoneyCents
            )
        } is ${transformBy100(taxCents)}"
    )

    cardType = "Montana-Pay"
    precedingMonthMoneyCents = 1_000_00uL
    transferMoneyCents = 7_000_00uL
    taxCents = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
    println("The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType is ${transformBy100(taxCents)}")

}

// Расчет комиссии
fun calcTax(
    cardType: String = "VK Pay",
    precedingMonthMoneyCents: ULong = 0uL,
    transferMoneyCents: ULong
): ULong {

    //Запишем ограничения в переменные
    val maxMastercardWithoutTax = 75_000_00uL
    val minVisaTax = 35_00uL
    // Коэффициенты не выношу в переменные, т.к. я еще плаваю в преобразовании типов kotlin

    return when (cardType) {
        "VK Pay" ->
            0uL

        "Visa", "Мир" -> {
            val taxFormulaResult = transferMoneyCents * 75uL / 10_000uL     // Как было бы без обязательного минимума
            if (minVisaTax >= taxFormulaResult) minVisaTax else taxFormulaResult // Подгоняем к условию
        }

        "Mastercard", "Maestro" -> {
            // Найдем превышение лимита
            // С суммы превышения (но не больше, чем с суммы текущего платежа) рассчитаем комиссию
            val transferOverLimit = when {
                (transferMoneyCents + precedingMonthMoneyCents <= maxMastercardWithoutTax) -> 0uL // не превышаем
                (precedingMonthMoneyCents >= maxMastercardWithoutTax) -> transferMoneyCents // превысили давно
                else -> transferMoneyCents + precedingMonthMoneyCents - maxMastercardWithoutTax // превысим текущим платежом
            }
            if (transferOverLimit == 0uL) 0uL else (transferOverLimit * 6uL / 1_000uL + 20_00uL)
        }

        else ->
            transferMoneyCents  // Все остальные системы - это пустая трата денег!
    }

}

fun transformBy100(number: ULong): String {
    return "${number / 100uL},${number % 100uL / 10uL}${number % 10uL}"
}
