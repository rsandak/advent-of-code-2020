fun countPassports(file: String): Int {

    return collectDocuments(readFile(file))
        .filter { it.isPassport() }
        .count()
}

fun countValidPassports(file: String): Int {

    return collectDocuments(readFile(file))
        .filter { it.isValidPassport() }
        .count()
}

fun collectDocuments(lines: List<String>) = sequence {

    var documentData = ""
    lines.forEach {
        if (it.isNotBlank()) {
            documentData += (" $it")
        } else {
            yield(Document(documentData.trim()))
            documentData = ""
        }
    }

    yield(Document(documentData.trim()))
}

class Document(data: String) {
    private val fields: List<Pair<PassportFields, String>> = data
        .split(" ")
        .map {
            val split = it.split(":")
            PassportFields.valueOf(split[0]) to split[1]
        }

    fun isPassport() = fields
        .map { it.first }
        .containsAll(PassportFields.requiredValues())

    fun isValidPassport(): Boolean {
        return isPassport() && fields.all { it.first.valid(it.second) }
    }
}

enum class PassportFields(val required: Boolean, val pattern: String) {
    byr(true, "([1][9][2-9][0-9])|([2][0][0][0-2])"),
    iyr(true, "([2][0][1][0-9])|([2][0][2][0])"),
    eyr(true, "([2][0][2][0-9])|([2][0][3][0])"),
    hgt(true, "((([1][5-8][0-9])|([1][9][0-3]))cm)|((([5][9])|([6][0-9])|([7][0-6]))in)"),
    hcl(true, "#([0-9]|[a-f]){6}"),
    ecl(true, "amb|blu|brn|gry|grn|hzl|oth"),
    pid(true, "\\d{9}"),
    cid(false, ".*");

    fun valid(value: String): Boolean {
        return Regex(pattern).matches(value)
    }

    companion object {
        fun requiredValues(): List<PassportFields> {
            return values()
                .filter { it.required }
        }
    }
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()

