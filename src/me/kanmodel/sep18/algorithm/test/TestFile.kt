package me.kanmodel.sep18.algorithm.test

import me.kanmodel.sep18.algorithm.util.DataHolder.print2D
import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.FileExecutor

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-20
 * Time: 21:48
 */
fun main(args: Array<String>) {
/*    val source = loadSourceData()
    println("load :\n$source")
    val data = Array(6) { IntArray(6) }
    println("分割第一步")
    var s1 = source.split("\n")
    s1.forEach { s: String -> println(s) }
    println(data.size)
    println("分割第二步")
    for (i in 0..5) {
        val s2 = s1[i].split("\t")
        for (j in 0..5) {
            data[i][j] = s2[j].toInt()
        }
    }*/
//    val data = loadData()
/*    val data = DataHolder.cost
    data[0][2] = Int.MAX_VALUE
    data[2][0] = Int.MAX_VALUE
    print2D(data)
//    saveData(data)
    DataHolder.save()*/
//    val a = FileExecutor.loadNamesData()
//    println()
//    a[1] = "一教"
    val a = Array<String>(14){"无"}
    FileExecutor.saveNamesData(a)
}