package me.kanmodel.sep18.algorithm.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-23
 * Time: 10:36
 */
object FileExecutor {
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

    private fun loadSourceData(): String {
        val file = File("data\\data.txt")
        if (!file.exists()) {
            saveData()
        }

        val fileInputStream = FileInputStream(file)
        val size = fileInputStream.available()

        val buffer = ByteArray(size)
        fileInputStream.read(buffer)
        fileInputStream.close()

        return String(buffer)
    }

    fun loadData(): Array<IntArray> {
        val source = loadSourceData()
        Log.i("Load Source :\n$source")

        Log.i("分割第一步")
        val s1 = source.split("\n")//以\n为分隔符号
        s1.forEach { s: String -> Log.i(s) }
        val data = Array(s1.size - 1) { IntArray(s1.size - 1) }

        Log.i("分割第二步")
        for (i in 0 until s1.size - 1) {//讲字符串转化为数字并存入邻接矩阵
            val s2 = s1[i].split("\t")//以\t为分隔符号
            for (j in 0 until s1.size - 1) {
                data[i][j] = s2[j].toInt()
            }
        }


        for (i in 0 until s1.size - 1) {
            data[i][i] = 0
        }//对角线置0

        return data
    }
}