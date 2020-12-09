fun loadGame(fileName: String): Game {
    val instructions = readFile(fileName)
        .map {
            val instructionParts = it.split(" ")
            Instruction(Instruction.Type.valueOf(instructionParts[0].toUpperCase()), instructionParts[1].toInt())
        }

    return Game(instructions)
}

class GameFixer(private val game: Game) {
    fun fix(): Game? {
        for (i in 0..game.instructions.size) {
            val fixedGame = game.fix(i)
            if (fixedGame.play() is Success) {
                return fixedGame
            }
        }

        return null
    }
}

class Game(val instructions: List<Instruction>) {
    private var acc = 0
    private val executedInstructions = mutableListOf<Int>()

    fun play(): Result {
        reset()
        return move(0)
    }

    private fun reset() {
        acc = 0
        executedInstructions.clear()
    }

    private fun move(index: Int): Result {
        if (executedInstructions.contains(index)) {
            return Failure(acc)
        }

        if (index == instructions.size) {
            return Success(acc)
        }

        executedInstructions.add(index)

        return when (instructions[index].type) {
            Instruction.Type.NOP -> move(index + 1)
            Instruction.Type.ACC -> {
                acc += instructions[index].step
                move(index + 1)
            }
            Instruction.Type.JMP -> move(index + instructions[index].step)
        }
    }

    fun fix(i: Int): Game {
        val clonedInstructions = instructions
            .map { Instruction(it.type, it.step) }

        if (clonedInstructions[i].type == Instruction.Type.JMP) {
            clonedInstructions[i].type = Instruction.Type.NOP
        } else if (clonedInstructions[i].type == Instruction.Type.NOP) {
            clonedInstructions[i].type = Instruction.Type.JMP
        }

        return Game(clonedInstructions)
    }
}

class Instruction(var type: Type, val step: Int) {
    enum class Type {
        NOP, ACC, JMP
    }
}

abstract class Result(open val value: Int)
class Success(override val value: Int) : Result(value)
class Failure(override val value: Int) : Result(value)

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()
