package fr.lidonis.adventofcode.y2020.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PasswordDatabaseTest {

    @Nested
    inner class SledRentalPolicyTest {

        @Test
        fun `check 1-3 a abcde is valid`() {
            val passwordDatabase = PasswordDatabase(listOf("1-3 a: abcde"))
            assertThat(passwordDatabase.countValidPasswordSledRentalPolicy()).isEqualTo(1)
        }

        @Test
        fun `1-3 b cdefg is invalid`() {
            val passwordDatabase = PasswordDatabase(listOf("1-3 b: cdefg"))
            assertThat(passwordDatabase.countValidPasswordSledRentalPolicy()).isEqualTo(0)
        }

        @Test
        fun `check 2-9 c ccccccccc is valid`() {
            val passwordDatabase = PasswordDatabase(listOf("2-9 c: ccccccccc"))
            assertThat(passwordDatabase.countValidPasswordSledRentalPolicy()).isEqualTo(1)
        }
    }

    @Nested
    inner class TobogganCorporatePolicyTest {

        @Test
        fun `check 1-3 a abcde is valid`() {
            val passwordDatabase = PasswordDatabase(listOf("1-3 a: abcde"))
            assertThat(passwordDatabase.countValidPasswordTobogganCorporatePolicy()).isEqualTo(1)
        }

        @Test
        fun `1-3 b cdefg is invalid`() {
            val passwordDatabase = PasswordDatabase(listOf("1-3 b: cdefg"))
            assertThat(passwordDatabase.countValidPasswordTobogganCorporatePolicy()).isEqualTo(0)
        }

        @Test
        fun `check 2-9 c ccccccccc is invalid`() {
            val passwordDatabase = PasswordDatabase(listOf("2-9 c: ccccccccc"))
            assertThat(passwordDatabase.countValidPasswordTobogganCorporatePolicy()).isEqualTo(0)
        }
    }
}