package fr.lidonis.adventofcode.y2020.day2

class PasswordDatabase(private val passwordRecords: List<String>) {

    fun countValidPasswordSledRentalPolicy() = passwordRecords.count { isValidRecordSledRentalPolicy(it) }

    fun countValidPasswordTobogganCorporatePolicy() = passwordRecords.count { isValidRecordTobogganCorporatePolicy(it) }

    private fun isValidRecordSledRentalPolicy(record: String): Boolean {
        val (policy, password) = record.split(": ")
        return SledRentalPolicy(policy).check(password)
    }

    private class SledRentalPolicy(policy: String) {

        private val range: IntRange
        private val letter: Char

        init {
            val (range, letter) = policy.split(' ')
            val (min, max) = range.split('-')
            this.range = IntRange(min.toInt(), max.toInt())
            this.letter = letter.first()
        }

        fun check(password: String) = range.contains(password.count { it == letter })
    }


    private fun isValidRecordTobogganCorporatePolicy(record: String): Boolean {
        val (policy, password) = record.split(": ")
        return TobogganCorporatePolicy(policy).check(password)
    }

    private class TobogganCorporatePolicy(policy: String) {

        private var position1: Int
        private var position2: Int
        private val letter: Char

        init {
            val (range, letter) = policy.split(' ')
            val (position1, position2) = range.split('-')
            this.position1 = position1.toInt()
            this.position2 = position2.toInt()
            this.letter = letter.first()
        }

        fun check(password: String) = validPosition(password, position1) xor validPosition(password, position2)

        private fun validPosition(password: String, position: Int) = password[position - 1] == letter
    }
}
