package me.kanmodel.sep18.algorithm.test

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
//    val data = loadData()
/*    val data = DataHolder.cost
    data[0][2] = Int.MAX_VALUE
    data[2][0] = Int.MAX_VALUE
    print2D(data)
//    saveData(data)
    DataHolder.save()*/
//    val a = FileExecutor.loadNamesData()
//    println()
//    a[1] = "һ��"
/*    val a = Array<String>(14){"��"}
    FileExecutor.saveNamesData(a)*/

    val a = FileExecutor.loadCoordinateData()
    println(a.size)

    printData(DataHolder.defaultCoordinateGenerate())
}


fun printData(data: Array<IntArray>){
    for (i in data) {
        for (j in i) {
            print("$j\t")
        }
        println()
    }

}