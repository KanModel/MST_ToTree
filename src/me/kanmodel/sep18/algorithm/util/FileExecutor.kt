package me.kanmodel.sep18.algorithm.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Description:文件执行类
 * @author: KanModel
 * Date: 2018-09-23
 * Time: 10:36
 */
object FileExecutor {
    const val BORDER = 50
    private const val PIC_WIDTH = 920 - BORDER
    private const val PIC_HEIGHT = 600 - BORDER

    /**
     * description: 保存邻接矩阵数组到文本文件
     * @param cost 邻接矩阵数组
     * @author: KanModel
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

    /**
     * description: 从data.txt文本文件加载字符串原始数据
     * @return: 字符串形式的数据
     * @author: KanModel
     */
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

    /**
     * description: 将字符串原始数据分割处理获得对应邻接矩阵数组
     * @return: 邻接矩阵数组
     * @author: KanModel
     */
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

    /**
     * description: 从namesData.txt文本文件加载字符串原始数据
     * @return:
     * @author: KanModel
     */
    private fun loadNamesSourceData(): String {
        val file = File("data\\namesData.txt")
        if (!file.exists()) {
            saveNamesData()
        }

        val fileInputStream = FileInputStream(file)
        val size = fileInputStream.available()

        val buffer = ByteArray(size)
        fileInputStream.read(buffer)
        fileInputStream.close()

        return String(buffer)
    }

    /**
     * description: 将字符串原始数据分割处理后得到对应名称数组
     * @return: 名称数组
     * @author: KanModel
     */
    fun loadNamesData(): Array<String> {
        val source = loadNamesSourceData()
        Log.i("Load Names Source :\n$source")

        val s1 = source.split("\n")//以\n为分隔符号
        s1.forEach { s: String -> Log.i(s) }
        val data = Array(s1.size - 1) { "" }
        for (i in 0 until data.size) {
            data[i] = s1[i]
        }

        return data
    }

    /**
     * description: 保存名称数据到文本文件
     * @param names 名称字符串数组
     * @author: KanModel
     */
    fun saveNamesData(names: Array<String> = Array(6) { "" }) {
        val file = File("data\\namesData.txt")
        if (!file.exists()) {
            file.parentFile.mkdirs()
        }

        var data = ""
        for (i in 0 until names.size) {
            data += "${names[i]}\n"
        }

        val fos = FileOutputStream(file)
        fos.write(data.toByteArray())
        fos.close()
    }

    /**
     * description: 从coorData.txt文本文件加载坐标数组
     * @return: 坐标数组
     * @author: KanModel
     */
    fun loadCoordinateData(): Array<IntArray> {
        val file = File("data\\coorData.txt")
        if (!file.exists()) {
            return DataHolder.defaultCoordinateGenerate()
        }

        val fileInputStream = FileInputStream(file)
        val size = fileInputStream.available()

        val buffer = ByteArray(size)
        fileInputStream.read(buffer)
        fileInputStream.close()

        val source = String(buffer)

        val s1 = source.split("\n")//以\n为分隔符号
        val data = Array(s1.size - 1) { IntArray(2) }

        for (i in 0 until s1.size - 1) {//讲字符串转化为数字并存入邻接矩阵
            val s2 = s1[i].split("\t")//以\t为分隔符号
            for (j in 0 until 2) {
                data[i][j] = s2[j].toInt()
            }
        }

        return data
    }

    /**
     * description: 保存坐标数组到文本文件
     * @param coor 坐标数组
     * @author: KanModel
     */
    fun saveCoordinateData(coor: Array<IntArray> = DataHolder.defaultCoordinateGenerate()) {
        val file = File("data\\coorData.txt")
        if (!file.exists()) {
            file.parentFile.mkdirs()
        }

        var data = ""
        for (i in 0 until coor.size) {
            for (j in 0 until 2) {
                data += "${coor[i][j]}\t"
            }
            data += "\n"
        }

        val fos = FileOutputStream(file)
        fos.write(data.toByteArray())
        fos.close()
    }

    /**
     * description: 从文件中加载数据并获得对应邻接矩阵 相对坐标 名称
     * @param file 文件地址
     * @author: KanModel
     */
    fun readFile(file: File) {
        var xMax = Double.MIN_VALUE
        var yMax = Double.MIN_VALUE
        var xMin = Double.MAX_VALUE
        var yMin = Double.MAX_VALUE

        /**
         * description: 获取相对画布大小的x坐标
         * @return: 以像素为单位的x坐标
         * @author: KanModel
         */
        fun getX(x: Double): Int {
            return ((x - xMin) / (xMax - xMin) * PIC_WIDTH).toInt()
        }

        /**
         * description: 获取相对画布大小的y坐标
         * @return: 以像素为单位的y坐标
         * @author: KanModel
         */
        fun getY(y: Double): Int {
            return PIC_HEIGHT - ((y - yMin) / (yMax - yMin) * PIC_HEIGHT).toInt() + 20
        }

        /**
         * description: 由两点坐标计算大致距离
         * @return: 距离米
         * @author: KanModel
         */
        fun dis(x1: Double, y1: Double, x2: Double, y2: Double): Double {
            return Math.sqrt(Math.pow(x1 - x2, 2.0) + Math.pow(y1 - y2, 2.0))
        }

        val fileInputStream = FileInputStream(file)
        val size = fileInputStream.available()

        val buffer = ByteArray(size)
        fileInputStream.read(buffer)
        fileInputStream.close()

        val source = String(buffer)

        val s1 = source.split("\n")//以\n为分隔符号

        val names = Array(s1.size) { "" }
        val sourceCoor = Array<DoubleArray>(s1.size) { DoubleArray(2) }
        val sCoor = Array<DoubleArray>(s1.size) { DoubleArray(2) }
        val coor = Array<IntArray>(s1.size) { IntArray(2) }
        val cost = Array<IntArray>(s1.size) { IntArray(s1.size) }
        for (i in 0 until s1.size) {
            val s2 = s1[i].split(" ")
            for (j in 0 until 3) {
                if (j == 0) {
                    names[i] = s2[j]
                } else {
                    sourceCoor[i][j - 1] = s2[j].toDouble() * 100000
                    sCoor[i][j - 1] = s2[j].toDouble()
                }
            }
        }
        for (i in sourceCoor) {
            if (i[0] > xMax) xMax = i[0]
            if (i[0] < xMin) xMin = i[0]
            if (i[1] > yMax) yMax = i[1]
            if (i[1] < yMin) yMin = i[1]
        }

        for (i in 0 until coor.size) {
            for (j in 0 until 2) {
                if (j == 0) {
                    coor[i][j] = getX(sourceCoor[i][j])
                } else {
                    coor[i][j] = getY(sourceCoor[i][j])
                }
            }
        }

        var data: String = ""
        for (i in 0 until coor.size) {
            for (j in 0 until 2) {
                data += "${coor[i][j]}\t"
            }
            data += "\n"
        }
        print("COOR:\n$data\n")
        names.forEach { Log.i(it) }


        for (i in 0 until cost.size) {
            for (j in 0 until cost.size) {
                cost[i][j] = (dis(sCoor[i][0], sCoor[i][1], sCoor[j][0], sCoor[j][1]) * 100.0 * 1000.0).toInt()
            }
        }

        saveData(cost)//保存新邻接矩阵
        saveNamesData(names)//保存新的名称
        saveCoordinateData(coor)//保存新的坐标
        DataHolder.reload()//重新加载数据
    }
}