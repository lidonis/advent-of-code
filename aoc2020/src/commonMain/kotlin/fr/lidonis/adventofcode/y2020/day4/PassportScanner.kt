package fr.lidonis.adventofcode.y2020.day4

private val HEIGHT_FORMAT = Regex("(\\d+)(cm|in)")
private val HAIR_COLOR_FORMAT = Regex("^#(\\d|[a-f]){6}$")
private val EYE_COLOR_FORMAT = Regex("^(amb|blu|brn|gry|grn|hzl|oth)$")
private val PASSPORT_ID_FORMAT = Regex("^\\d{9}$")
private const val MIN_BIRTH_YEAR = 1920
private const val MAX_BIRTH_YEAR = 2002
private const val MIN_ISSUE_YEAR = 2010
private const val MAX_ISSUE_YEAR = 2020
private const val MIN_EXPIRATION_YEAR = 2020
private const val MAX_EXPIRATION_YEAR = 2030
private const val MIN_HEIGHT_CM = 150
private const val MAX_HEIGHT_CM = 193
private const val MIN_HEIGHT_IN = 59
private const val MAX_HEIGHT_IN = 76

class PassportScanner(text: String) {

    private var passportDataList = text.split("\n\n")
        .map { block ->
            block.split("\n").flatMap { it.split(" ") }
                .map { it.split(":") }
                .map { (key, value) -> key to value }
        }
        .map { PassportData(it.toMap().withDefault { null }) }

    fun countValidRequiredField() = passportDataList.count { it.isValidateRequiredField() }

    fun countValidValues() = passportDataList.count { it.isValidValue() }

    class PassportData(map: Map<String, String?>) {
        private val byr: String? by map
        private val iyr: String? by map
        private val eyr: String? by map
        private val hgt: String? by map
        private val hcl: String? by map
        private val ecl: String? by map
        private val pid: String? by map
        private val cid: String? by map

        fun isValidateRequiredField() =
            byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null

        fun isValidValue() = isValidateRequiredField() &&
            byr?.toInt() in MIN_BIRTH_YEAR..MAX_BIRTH_YEAR &&
            iyr?.toInt() in MIN_ISSUE_YEAR..MAX_ISSUE_YEAR &&
            eyr?.toInt() in MIN_EXPIRATION_YEAR..MAX_EXPIRATION_YEAR &&
            validateHeight() &&
            hcl?.matches(HAIR_COLOR_FORMAT) == true &&
            ecl?.matches(EYE_COLOR_FORMAT) == true &&
            pid?.matches(PASSPORT_ID_FORMAT) == true

        private fun validateHeight() =
            HEIGHT_FORMAT.find(hgt ?: "")?.destructured?.let { (height, unit) ->
                when (unit) {
                    "cm" -> height.toInt() in MIN_HEIGHT_CM..MAX_HEIGHT_CM
                    "in" -> height.toInt() in MIN_HEIGHT_IN..MAX_HEIGHT_IN
                    else -> false
                }
            } ?: false

        override fun toString(): String {
            return if (isValidateRequiredField()) {
                "Passport(byr=$byr, iyr=$iyr, eyr=$eyr, hgt=$hgt, hcl=$hcl, ecl='$ecl', pid=$pid, cid=$cid)"
            } else {
                "Missing values in passport"
            }
        }
    }
}
