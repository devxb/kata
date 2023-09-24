package rangecheck

class Range(private val index: Int) : Comparable<Range> {

    override fun compareTo(other: Range): Int = index.compareTo(other.index)
}
