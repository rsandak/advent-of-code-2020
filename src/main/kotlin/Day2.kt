import java.util.stream.Stream

class PasswordChecker(var file: String) {
    fun run(createPolicy: (String) -> PasswordPolicy): Long {
        val validPasswords = readFile(file)
            .stream()
            .map { createPolicy(it) }

        return countValidPassword(validPasswords)
    }

    private fun countValidPassword(validPasswords: Stream<PasswordPolicy>) =
        validPasswords
            .filter { it.isValid() }
            .count()
}

interface PasswordPolicy {
    fun isValid(): Boolean
}

class OldPasswordPolicy(input: String) : PasswordPolicy {
    private var min: Int
    private var max: Int
    private var letter: String
    private var password: String

    init {
        val parts = input.split(" ")
        val multiplicity = parts[0].split("-")

        this.min = multiplicity[0].toInt()
        this.max = multiplicity[1].toInt()
        this.letter = parts[1].replace(":", "")
        this.password = parts[2]
    }

    override fun isValid(): Boolean {
        val count = password.count { it == letter.toCharArray()[0] }

        return count in min..max
    }
}

class NewPasswordPolicy(input: String) : PasswordPolicy {
    private var firstPosition: Int
    private var secondPosition: Int
    private var letter: String
    private var password: String

    init {
        val parts = input.split(" ")
        val multiplicity = parts[0].split("-")

        this.firstPosition = multiplicity[0].toInt()
        this.secondPosition = multiplicity[1].toInt()
        this.letter = parts[1].replace(":", "")
        this.password = parts[2]
    }

    override fun isValid(): Boolean {
        return containsOnPosition(firstPosition) && !containsOnPosition(secondPosition)
                || !containsOnPosition(firstPosition) && containsOnPosition(secondPosition)
    }

    private fun containsOnPosition(position: Int) =  password.substring(position - 1, position) == letter
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()

