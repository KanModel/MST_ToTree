package me.kanmodel.sep18.algorithm

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
    val data = loadData()
    print2D(data)
    saveData(data)
}