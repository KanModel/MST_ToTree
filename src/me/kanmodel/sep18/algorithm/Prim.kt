package me.kanmodel.sep18.algorithm

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-18
 * Time: 19:00
 */
fun main(args: Array<String>) {
//    val arg = Array(5){IntArray(5)}
    val treeFrame = TreeFrame()
    treeFrame.isVisible = true

    val dim = 6
/*    val cost = arrayOf(
            intArrayOf(0, 10, Int.MAX_VALUE, 30, 45, Int.MAX_VALUE),
            intArrayOf(10, 0, 50, Int.MAX_VALUE, 40, 25),
            intArrayOf(Int.MAX_VALUE, 50, 0, Int.MAX_VALUE, 35, 15),
            intArrayOf(30, Int.MAX_VALUE, Int.MAX_VALUE, 0, Int.MAX_VALUE, 20),
            intArrayOf(45, 40, 35, Int.MAX_VALUE, 0, 55),
            intArrayOf(Int.MAX_VALUE, 25, 15, 20, 55, 0)
    )*/
    val cost = loadData()

    val coor = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(3, 1),
            intArrayOf(5, 2),
            intArrayOf(1, 3),
            intArrayOf(3, 3),
            intArrayOf(2, 5)
    )

    val picToTree = Array(dim) { IntArray(dim) { Int.MAX_VALUE } }

    print2D(cost)

    var minCost = 0
    var k = 0
    var l = 1
    for (i in 0 until dim) {
        for (j in i + 1 until dim) {
            if (cost[i][j] < cost[k][l]) {
                k = i;
                l = j
            }
        }
    }
    println("cost[${k + 1}][${l + 1}] ${cost[k][l]} 为最短边")

    picToTree[k][l] = cost[k][l]
    picToTree[l][k] = cost[k][l]
//    TreeFrame(picToTree.copyOf(), coor).isVisible = true
//    treeFrame.addTreePanel(copyArray(picToTree), coor)
    treeFrame.addTreePanel(Array(dim) { it -> picToTree[it].copyOf() }, coor)
    print2D(picToTree)
    Thread.sleep(100)

    minCost += cost[k][l]
    val tree: ArrayList<Edge> = ArrayList()
    tree.add(Edge(k, l))

    val near = IntArray(dim)
    /*near赋初始值*/
    for (i in 0 until dim) {
        if (cost[i][l] < cost[i][k]) {
            near[i] = l + 1
        } else {
            near[i] = k + 1
        }
    }
    near[k] = 0
    near[l] = 0
    near.forEach { print("$it ") }
    println()

    for (i in 1 until dim - 1) {
        var min = 0
        var costTmp = Int.MAX_VALUE
        for (j in 0 until dim) {
            if (near[j] != 0) {
                if (costTmp > cost[j][near[j] - 1]) {
                    costTmp = cost[j][near[j] - 1]
                    min = j
                }
            }
        }
        println("最短路径权值${min + 1} - ${near[min]}：$costTmp")
        minCost += costTmp

        picToTree[min][near[min] - 1] = cost[min][near[min] - 1]
        picToTree[near[min] - 1][min] = cost[min][near[min] - 1]
//        TreeFrame(picToTree.copyOf(), coor).isVisible = true
        treeFrame.addTreePanel(Array(dim) { it -> picToTree[it].copyOf() }, coor)
        print2D(picToTree)
        Thread.sleep(100)

        near[min] = 0

        for (j in 0 until dim) {
            if (near[j] != 0 && cost[j][near[j] - 1] > cost[j][min]) {
                near[j] = min + 1
            }
        }
        near.forEach { print("$it ") }
        println()
    }
    treeFrame.isVisible = true
}

/**
 * 数据类：边类
 */
data class Edge(val first: Int, val second: Int)

/**
 * @param arg: 二维整型数组
 * 输出二维数组
 *
 */
fun print2D(arg: Array<IntArray>) {
    for (i in arg) {
        for (j in i) {
            if (j == Int.MAX_VALUE) {
                System.out.printf("%-2s ", "∞")
//                print("∞ ")
            } else {
                System.out.printf("%-3s ", j)
//                print("$j ")
            }
        }
        println()
    }
}