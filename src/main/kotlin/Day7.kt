fun countContainingBag(rulesFile: String, name: String): Int {
    val bags = readBags(rulesFile)

    val acc: MutableSet<String> = mutableSetOf()
    foundBagContainingMyBag(acc, bags, name)

    return acc.count()
}

fun foundBagContainingMyBag(acc: MutableSet<String>, bags: Sequence<Bag>, myBagName: String) {
    bags
        .filter {
            it.content
                .map { it.second }
                .contains(myBagName)
        }
        .forEach {
            acc.add(it.type)
            foundBagContainingMyBag(acc, bags, it.type)
        }
}

fun countRequiredInside(rulesFile: String, name: String): Int {
    val bags = readBags(rulesFile)

    return countBagsInSet(bags, name) - 1
}

private fun countBagsInSet(bags: Sequence<Bag>, name: String): Int {
    val bag = findBag(bags, name)

    return 1 + bag
        .content
        .sumBy { it.first * countBagsInSet(bags, it.second) }
}

private fun findBag(bags: Sequence<Bag>, name: String): Bag {
    return bags
        .filter { it.type == name }
        .first()
}

class Bag(val type: String, val content: List<Pair<Int, String>>)

fun readBags(file: String) = sequence {
    readFile(file)
        .map {
            val rules = it.split(" bags contain ")
            val name = rules[0]

            if (rules[1] != "no other bags.") {
                val content = rules[1].split(", ")
                    .map { it.split(" ") }
                    .map { it[0].toInt() to (it[1] + " " + it[2]) }

                yield(Bag(name, content))
            } else {
                yield(Bag(name, emptyList()))
            }
        }
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()
