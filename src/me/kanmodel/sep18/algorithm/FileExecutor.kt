package me.kanmodel.sep18.algorithm

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-23
 * Time: 10:36
 */
fun saveData(cost: Array<IntArray> = arrayOf(
        intArrayOf(0, 10, Int.MAX_VALUE, 30, 45, Int.MAX_VALUE),
        intArrayOf(10, 0, 50, Int.MAX_VALUE, 40, 25),
        intArrayOf(Int.MAX_VALUE, 50, 0, Int.MAX_VALUE, 35, 15),
        intArrayOf(30, Int.MAX_VALUE, Int.MAX_VALUE, 0, Int.MAX_VALUE, 20),
        intArrayOf(45, 40, 35, Int.MAX_VALUE, 0, 55),
        intArrayOf(Int.MAX_VALUE, 25, 15, 20, 55, 0))) {


    val file = File("data\\data.txt")
    if (!file.exists()) {
        file.parentFile.mkdirs()
    }

    var data = ""
    for (i in 0 until cost.size) {
        for (j in 0 until cost.size) {
            data += "${cost[i][j]}\t"
        }
        data += "\n"
    }

    val fos = FileOutputStream(file)
//    fos.write("Te\tst".toByteArray())
    fos.write(data.toByteArray())

    fos.close()
}

fun loadSourceData(): String {
//    val str = ""
    val file = File("data\\data.txt")

    val fileInputStream = FileInputStream(file)
    val size = fileInputStream.available()

    val buffer = ByteArray(size)
    fileInputStream.read(buffer)
    fileInputStream.close()

    return String(buffer)
}

fun loadData(): Array<IntArray> {
    val source = loadSourceData()
    println("load :\n$source")
    println("分割第一步")
    val s1 = source.split("\n")
    s1.forEach { s: String -> println(s) }
//    println("第一步分割后分为 ${s1.size} 个部分")
    val data = Array(s1.size - 1) { IntArray(s1.size - 1) }
    println("分割第二步")
    for (i in 0 until s1.size - 1) {
        val s2 = s1[i].split("\t")
//        println("第二步分割后分为 ${s2.size} 个部分")
        for (j in 0 until s1.size - 1) {
            data[i][j] = s2[j].toInt()
        }
    }
    return data
}