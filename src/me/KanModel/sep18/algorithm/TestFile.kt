package me.KanModel.sep18.algorithm

import me.KanModel.sep18.algorithm.print2D

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
    println("�ָ��һ��")
    var s1 = source.split("\n")
    s1.forEach { s: String -> println(s) }
    println(data.size)
    println("�ָ�ڶ���")
    for (i in 0..5) {
        val s2 = s1[i].split("\t")
        for (j in 0..5) {
            data[i][j] = s2[j].toInt()
        }
    }*/
    val data = loadData()
    print2D(data)
    saveData(data)
}