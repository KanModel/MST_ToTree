package me.kanmodel.sep18.algorithm.util

import me.kanmodel.sep18.algorithm.util.FileExecutor.loadData
import me.kanmodel.sep18.algorithm.util.FileExecutor.saveData
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 17:54
 */
object DataHolder {
    var cost = loadData()

    fun reload() {
        cost = loadData()
    }

    fun changeDimension(newDim: Int) {
        val newCost = Array(newDim) { IntArray(newDim) { Int.MAX_VALUE } }
        if (newDim > cost.size) {
            for (i in 0 until cost.size) {
                for (j in 0 until cost.size) {
                    newCost[i][j] = cost[i][j]
                }
            }
        } else {
            for (i in 0 until newDim) {
                for (j in 0 until newDim) {
                    newCost[i][j] = cost[i][j]
                }
            }
        }
        save(newCost)
        reload()
    }

    fun defaultCoordinateGenerate(): Array<IntArray> {
        val newCoor = Array(cost.size) { IntArray(2) }
        val radius = 200
        val xDeviation = 300
        val yDeviation = 300
        for (i in 0 until cost.size) {
//            val deviation: Int = when (i % 3) {
//                0 -> 1
//                1 -> -1
//                2 -> 2
//                else -> {
//                    1
//                }
//            }
            val angle: Double = 360.0 / cost.size * i
            println("x: ${cos(angle / 180 * PI) * radius} y: ${sin(angle / 180 * PI) * radius}")
            newCoor[i][0] = (cos(angle / 180 * PI) * radius).toInt() + xDeviation
            newCoor[i][1] = (sin(angle / 180 * PI) * radius).toInt() + yDeviation
        }
        return newCoor
    }

    fun save(data: Array<IntArray> = cost) {
        saveData(data)
    }
}