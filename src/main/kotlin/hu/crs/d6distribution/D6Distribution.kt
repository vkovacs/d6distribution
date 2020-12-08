@file:Suppress("LocalVariableName")

package hu.crs.d6distribution

import javafx.scene.paint.Color
import java.util.*
import java.util.concurrent.ThreadLocalRandom

fun distribution() : SortedMap<Int, Int> {
    val distribution = mutableMapOf<Int, Int>()

    for (i in 1..1000000) {
        val d6_1 = (1..6).random()
        val d6_2 = (1..6).random()
        val d6_3 = (1..6).random()

        val sum_d6_1_2 = d6_1 + d6_2
        val sum_d6_1_3 = d6_1 + d6_3
        val sum_d6_2_3 = d6_2 + d6_3

        distribution[sum_d6_1_2] = (distribution[sum_d6_1_2] ?: 0) + 1
        distribution[sum_d6_1_3] = (distribution[sum_d6_1_3] ?: 0) + 1
        distribution[sum_d6_2_3] = (distribution[sum_d6_2_3] ?: 0) + 1
    }

    return distribution.toSortedMap()
}