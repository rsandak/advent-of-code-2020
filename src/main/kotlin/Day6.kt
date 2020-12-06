fun sumQuestionsAnyoneAnsweredYes(file: String): Int {
    return getGroups(readFile(file))
        .sumBy { it.questionsAnyoneAnsweredYes() }
}

fun sumQuestionsEveryoneAnsweredYes(file: String): Int {
    return getGroups(readFile(file))
        .sumBy { it.questionsEveryoneAnsweredYes() }
}

class Group(private val answers: List<String>) {

    fun questionsAnyoneAnsweredYes(): Int {
        return answers
            .flatMap { it.toList() }
            .toSet()
            .count()
    }

    fun questionsEveryoneAnsweredYes(): Int {
        return answers
            .map { it.toSet() }
            .reduce { acc, it -> acc.intersect(it) }
            .size
    }
}

private fun getGroups(lines: List<String>) = sequence {

    var personAnswers = mutableListOf<String>()
    lines.forEach {
        if (it.isNotBlank()) {
            personAnswers.add(it)
        } else {
            yield(Group(personAnswers))
            personAnswers = mutableListOf()
        }
    }

    yield(Group(personAnswers))
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()

