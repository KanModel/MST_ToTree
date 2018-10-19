package me.kanmodel.sep18.algorithm.util

import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.PIC_HEIGHT
import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.PIC_WIDTH
import me.kanmodel.sep18.algorithm.util.FileExecutor.loadData
import me.kanmodel.sep18.algorithm.util.FileExecutor.loadNamesData
import me.kanmodel.sep18.algorithm.util.FileExecutor.saveCoordinateData
import me.kanmodel.sep18.algorithm.util.FileExecutor.saveData
import me.kanmodel.sep18.algorithm.util.FileExecutor.saveNamesData
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Description:���ݳ�����
 * @author: KanModel
 * Date: 2018-09-25
 * Time: 17:54
 */
object DataHolder {
    const val PRIM = 0
    const val KRUSKAL = 1

    var cost = loadData()
    var names = loadNamesData()
    var coor = getCoordinate()
    var algorithmSelect = PRIM;//Ĭ��ѡ���㷨

    /**
     * description: ����������������
     * @author: KanModel
     */
    fun reload() {
        cost = loadData()
        names = loadNamesData()
        coor = getCoordinate()
    }

    /**
     * description: �ı��ڽӾ���ά��
     * @param newDim �µ�ά��
     * @author: KanModel
     */
    fun changeDimension(newDim: Int) {
        val newCost = Array(newDim) { i ->
            IntArray(newDim) { j ->
                when (i == j) {
                    true -> 0
                    false -> Int.MAX_VALUE
                }
            }
        }
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

        val newNames: Array<String> = Array(newDim) { "" }
        if (newDim > names.size) {
            for (i in 0 until names.size) {
                newNames[i] = names[i]
            }
        } else {
            for (i in 0 until newDim) {
                newNames[i] = names[i]
            }
        }

        saveNamesData(newNames)
        saveCoor(defaultCoordinateGenerate(newDim))
        reload()
    }

    /**
     * description: ͨ���ļ�ִ�����ȡ��������
     * @return: ��������
     * @author: KanModel
     */
    private fun getCoordinate(): Array<IntArray> {
        return FileExecutor.loadCoordinateData()
    }

    /**
     * description: ��������Ĭ�ϵ��������飬ʹ������������ɶ�Ӧ�����󻯱���Ȩ�ؽ���
     * @param dim ά��
     * @return: ��������
     * @author: KanModel
     */
    fun defaultCoordinateGenerate(dim: Int = cost.size): Array<IntArray> {
        val newCoor = Array(dim) { IntArray(2) }
        val radius = 250
        val xDeviation = PIC_WIDTH/2
        val yDeviation = PIC_HEIGHT/2
        for (i in 0 until dim) {
            val angle: Double = 360.0 / dim * i
            println("x: ${cos(angle / 180 * PI) * radius} y: ${sin(angle / 180 * PI) * radius}")
            newCoor[i][0] = (cos(angle / 180 * PI) * radius).toInt() + xDeviation
            newCoor[i][1] = (sin(angle / 180 * PI) * radius).toInt() + yDeviation
        }
        return newCoor
    }

    /**
     * description: �����ڽӾ�������
     * @param data �ڽӾ�������
     * @author: KanModel
     */
    fun save(data: Array<IntArray> = cost) {
        saveData(data)
    }

    /**
     * description: ������������
     * @param data ��������
     * @author: KanModel
     */
    fun saveNames(data: Array<String> = names) {
        saveNamesData(data)
    }

    /**
     * description: ������������
     * @param data ��������
     * @author: KanModel
     */
    fun saveCoor(data: Array<IntArray> = coor) {
        saveCoordinateData(data)
    }

    /**
     * �����ά���� ����Int.Max_VALUE�����
     * @param arg: ��ά��������
     */
    @JvmStatic
    fun print2D(arg: Array<IntArray>) {
        for (i in arg) {
            for (j in i) {
                if (j == Int.MAX_VALUE) {
                    System.out.printf("%-2s ", "��")
                } else {
                    System.out.printf("%-3s ", j)
                }
            }
            println()
        }
    }
}