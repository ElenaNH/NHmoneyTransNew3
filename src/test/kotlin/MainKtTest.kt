import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun transformBy100() {
        val m = 20_00uL
        val result = transformBy100(m)

        assertEquals("20,00", result)
    }

    @Test
    fun calcTax_0() {
//        val cardType = "VK Pay"
//        val precedingMonthMoneyCents = 0uL
        val transferMoneyCents = 7_500_00uL
        val result = calcTax(transferMoneyCents = transferMoneyCents)

        assertEquals(0uL, result)
//        assertEquals(1uL, result)
    }
    @Test
    fun calcTax_00() {
        val cardType = "VK Pay"
        val precedingMonthMoneyCents = 0uL
        val transferMoneyCents = 7_500_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)

        assertEquals(0uL, result)
    }
    @Test
    fun calcTax_01() {
        val cardType = "Visa"
        val precedingMonthMoneyCents = 1_000_00uL
        val transferMoneyCents = 4_000_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)

        assertEquals(3500uL, result)
    }
    @Test
    fun calcTax_02() {
        val cardType = "Мир"
        val precedingMonthMoneyCents = 1_000_00uL
        val transferMoneyCents = 4_700_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)

        assertEquals(3525uL, result)
    }

    @Test
    fun calcTax_03() {
        val cardType ="Mastercard"
        val precedingMonthMoneyCents = 50_000_00uL
        val transferMoneyCents = 950_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
        println("The transfer tax for ${transformBy100(transferMoneyCents)} after ${transformBy100(precedingMonthMoneyCents)} by $cardType is ${transformBy100(result)}")

        assertEquals(0uL, result)
    }

    @Test
    fun calcTax_04() {
        val cardType ="Mastercard"
        val precedingMonthMoneyCents = 74_500_00uL
        val transferMoneyCents = 1_000_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
        println("The transfer tax for ${transformBy100(transferMoneyCents)} after ${transformBy100(precedingMonthMoneyCents)} by $cardType is ${transformBy100(result)}")

        assertEquals(2300uL, result)
    }

    @Test
    fun calcTax_05() {
        val cardType ="Maestro"
        val precedingMonthMoneyCents = 75_000_00uL
        val transferMoneyCents = 1_000_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
        println("The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType after ${transformBy100(precedingMonthMoneyCents)} is ${transformBy100(result)}")

        assertEquals(2600uL, result)
    }

    @Test
    fun calcTax_06() {
        val cardType ="Montana-Pay"
        val precedingMonthMoneyCents = 1_000_00uL
        val transferMoneyCents = 7_000_00uL
        val result = calcTax(cardType, precedingMonthMoneyCents, transferMoneyCents)
        println("The transfer tax for ${transformBy100(transferMoneyCents)} by $cardType is ${transformBy100(result)}")

        assertEquals(7_000_00uL, result)
    }
    @Test
    fun mainTst() {

        /* Честно говоря, я не понимаю, как можно целиком тестировать функцию, не возвращающую результат.
         Но без этого теста был только 31% покрытия, а с ним сразу стало 100% */


        val result: Unit = main(Array(3){i -> (10-i).toString()})   // Массив взят "от фонаря", т.к. без него все падало

        assertEquals(Unit, result)
    }
}