import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day4Test {

    @Test
    fun `first check 111111 meets criteria`() {
        Assertions.assertTrue(PasswordChecker.check1(111111))
    }

    @Test
    fun `first check 22450 does not meet criteria never decrease`() {
        Assertions.assertFalse(PasswordChecker.check1(22450))
    }

    @Test
    fun `first check 123789 does not meet criteria double`() {
        Assertions.assertFalse(PasswordChecker.check1(123789))
    }

    @Test
    fun `second check 123444 does not meet criteria no larger than two group`() {
        Assertions.assertFalse(PasswordChecker.check2(123444))
    }

    @Test
    fun `second check 111122 meets criteria`() {
        Assertions.assertTrue(PasswordChecker.check2(111122))
    }
}