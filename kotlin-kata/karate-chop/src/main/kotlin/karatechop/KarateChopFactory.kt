package karatechop

object KarateChopFactory {

    val binaryImplementedKarateChop: KarateChop = KarateChop { seek, candidates ->
        val sortedCandidates = candidates.sorted()
        var left = 0
        var right = sortedCandidates.size - 1

        while (left <= right) {
            val mid = (left + right) / 2
            if (sortedCandidates[mid] == seek) {
                return@KarateChop true
            }
            if (sortedCandidates[mid] >= seek) {
                right = mid - 1
                continue
            }
            left = mid + 1
        }

        return@KarateChop false
    }

    val binaryUtilKarateChop: KarateChop = KarateChop { seek, candidates ->
        val sortedCandidates = candidates.sorted()
        return@KarateChop sortedCandidates.binarySearch(seek, 0) >= 0
    }

}
