val input = """.......S.......
...............
.......^.......
...............
......^.^......
...............
.....^.^.^.....
...............
....^.^...^....
...............
...^.^...^.^...
...............
..^...^.....^..
...............
.^.^.^.^.^...^.
..............."""

var answer = 0L
var allBeamIndexes = mutableMapOf<Int, Set<Int>>()

fun traverse(
    allBeamIndexes: MutableMap<Int, Set<Int>>,
    string: String,
    index: Int
) {
    val previousBeamIndexes = allBeamIndexes[index - 1] ?: return
    previousBeamIndexes.forEach { beamIndex ->
        if (string[beamIndex] == '^') {
            answer++
            allBeamIndexes[index] = allBeamIndexes.getOrDefault(index, emptySet()) + setOf(
                beamIndex - 1,
                beamIndex + 1
            )
        } else {
            allBeamIndexes[index] =
                allBeamIndexes.getOrDefault(index, emptySet()) + setOf(beamIndex)
        }
    }
}

input.split(System.lineSeparator()).forEachIndexed { index, string ->
    val entryIndex = string.indexOf("S")
    if (entryIndex != -1) {
        println(string)
        allBeamIndexes[index] = setOf(entryIndex)
        return@forEachIndexed
    }
    val previousBeamIndexes = allBeamIndexes[index - 1] ?: return

    if (string.contains('^')) {
        traverse(allBeamIndexes, string, index)
    } else {
        allBeamIndexes[index] = previousBeamIndexes
    }
}
println("answer: $answer")