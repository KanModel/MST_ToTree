package me.kanmodel.sep18.algorithm.algorithm

import me.kanmodel.sep18.algorithm.gui.TreeFrame
import me.kanmodel.sep18.algorithm.gui.TreeStepPanel
import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.DataHolder.print2D

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: KanModel
 * Date: 2018-09-23
 * Time: 15:24
 */

class Kruskal {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            getTreeFrame().isVisible = true
        }

        fun getTreeFrame(): TreeFrame {
            val treeFrame = TreeFrame()

            val dim = DataHolder.cost.size  //数组长度
            DataHolder.reload()
            val cost = DataHolder.cost
            //原始数组
            var newCost = Array(dim) { it -> cost[it].copyOf() }
            val tarCost = Array<IntArray>(dim) { IntArray(dim) { Int.MAX_VALUE } }

            var minCost = 0
            var minCostX = 0
            var minCostY = 0 // 当前最短路径节点
            //成环判断
            var parent = IntArray(dim)
            var fir: Int
            var sec: Int

            fun getMinCost(): Int {
                var k = 0
                var l = 1
                for (i in 0 until dim) {
                    for (j in i + 1 until dim) {
                        if (newCost[i][j] < newCost[k][l]) {
                            k = i
                            l = j
                        }
                    }
                }//筛选最短路径
                minCostX = k
                minCostY = l
                return newCost[k][l]
            }  //获取当前最短路径节点

            fun select(cost: Array<IntArray>, i: Int, j: Int): Boolean {
                fir = find(parent, i)
                sec = find(parent, j)
                if (fir != sec) {
                    parent[fir] = sec
                    println("连接最短路径${i + 1}--${j + 1}")
                    tarCost[i][j] = newCost[i][j]
                    return true
                } else {
                    cost[i][j] = Int.MAX_VALUE
                    cost[j][i] = Int.MAX_VALUE
                    return false
                }
            }//成环判断

            var count = 0
            while (count < dim - 1) {
                getMinCost()
                while ((select(newCost, minCostX, minCostY))) {
                    count++
                    println("${count}")
                    print2D(tarCost)
                    treeFrame.addTreePanel(Array(dim) { it -> tarCost[it].copyOf() }, DataHolder.coor)
                }
            }
//*********************//*********************//*********************

            return treeFrame
        }

        fun getTreeStepPanel(): TreeStepPanel {
            val treeStepPanel = TreeStepPanel()

            val dim = DataHolder.cost.size  //数组长度
            DataHolder.reload()
            val cost = DataHolder.cost
            //原始数组
            var newCost = Array(dim) { it -> cost[it].copyOf() }
            val tarCost = Array<IntArray>(dim) { IntArray(dim) { Int.MAX_VALUE } }

            var minCost = 0
            var minCostX = 0
            var minCostY = 0 // 当前最短路径节点
            //成环判断
            var parent = IntArray(dim)
            var fir: Int
            var sec: Int

            fun getMinCost(): Int {
                var k = 0
                var l = 1
                for (i in 0 until dim) {
                    for (j in i + 1 until dim) {
                        if (newCost[i][j] < newCost[k][l]) {
                            k = i
                            l = j
                        }
                    }
                }//筛选最短路径
                minCostX = k
                minCostY = l
                return newCost[k][l]
            }  //获取当前最短路径节点

            fun select(cost: Array<IntArray>, i: Int, j: Int): Boolean {
                fir = find(parent, i)
                sec = find(parent, j)
                if (fir != sec) {
                    parent[fir] = sec
                    println("连接最短路径${i + 1}--${j + 1}")
                    tarCost[i][j] = newCost[i][j]
                    minCost += newCost[i][j]
                    return true
                } else {
                    cost[i][j] = Int.MAX_VALUE
                    cost[j][i] = Int.MAX_VALUE
                    return false
                }
            }//成环判断

            var count = 0
            while (count < dim - 1) {
                getMinCost()
                while ((select(newCost, minCostX, minCostY))) {
                    count++
                    println("${count}")
                    print2D(tarCost)
                    treeStepPanel.addTreePanel(Array(dim) { it -> tarCost[it].copyOf() }, DataHolder.coor)
                }
            }
            println("最短路径: $minCost")
            treeStepPanel.addResultPanel(minCost)
            return treeStepPanel
        }

        fun find(parent: IntArray, result: Int): Int {
            var result_var = result
            while (parent[result_var] > 0) {
                result_var = parent[result_var]
            }
            return result_var
        }
    }
}