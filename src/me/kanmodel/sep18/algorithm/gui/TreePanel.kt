package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Description: 由给定邻接矩阵及坐标绘制无向图
 * @author KanModel
 * Date: 2018-10-13
 * Time: 10:20
 * @param cost Array<IntArray> 邻接矩阵
 * @param coor Array<IntArray> 结点坐标
 * @param dim Int 邻接矩阵维度
 */
class TreePanel(private val cost: Array<IntArray>, private val coor: Array<IntArray> = DataHolder.defaultCoordinateGenerate(), val dim: Int = DataHolder.cost.size) : JPanel() {
    private val weightList = ArrayList<Weight>()
    private val nameList = ArrayList<Name>()

    init {
        preferredSize = Dimension(PIC_WIDTH, PIC_HEIGHT)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        drawPicture(g!!)
    }

    /**
     * description: 画无向图
     * @author: KanModel
     */
    private fun drawPicture(g: Graphics) {
        val g2d = g.create() as Graphics2D

        for (i in 0 until dim) {
            for (j in i + 1 until dim) {
                if (cost[i][j] != Int.MAX_VALUE) {
                    var firstName = (i + 1).toString()
                    var secondName = (j + 1).toString()
                    if (DataHolder.names[i] != "") firstName = DataHolder.names[i]
                    if (DataHolder.names[j] != "") secondName = DataHolder.names[j]
                    drawP2P(g, coor[i][0] * COEFFICIENT, coor[i][1] * COEFFICIENT, coor[j][0] * COEFFICIENT, coor[j][1] * COEFFICIENT, cost[i][j], firstName, secondName)
                }
            }
        }
        g2d.color = Color.RED//权重字体使用红色
        weightList.forEach { g2d.drawString(it.w.toString(), it.x, it.y) }//稍后打印权重
        g2d.color = Color.BLUE
        nameList.forEach { g2d.drawString(it.name, it.x, it.y) }
    }

    /**
     * description:
     * @param x1 第1个点x坐标
     * @param y1 第1个点y坐标
     * @param x2 第2个点y坐标
     * @param y2 第2个点y坐标
     * @param firstName 第1个点名称
     * @param secondName 第2个点名称
     * @author: KanModel
     */
    private fun drawP2P(g: Graphics, x1: Int, y1: Int, x2: Int, y2: Int, w: Int = 0, firstName: String = "", secondName: String = "") {
        drawLine(g, x1, y1, x2, y2, w)
        drawCircle(g, x1, y1, firstName)
        drawCircle(g, x2, y2, secondName)
    }

    /**
     * description: 画圆及记录名称
     * @param x x坐标
     * @param y y坐标
     * @param name 名称
     * @author: KanModel
     */
    private fun drawCircle(g: Graphics, x: Int = 0, y: Int = 0, name: String = "") {
        val g2d = g.create() as Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.color = Color.pink
        g2d.fillArc(x, y, RADIUS, RADIUS, 0, 360)
        g2d.color = Color.BLUE
        nameList.add(Name((x + RADIUS / 2 + 5), (y + RADIUS / 2 - 5), name))
//        g2d.drawString(name, (x + RADIUS / 2 + 5), (y + RADIUS / 2 - 5))//打印名字
        g2d.dispose()
    }

    /**
     * description: 画一条线
     * @author: KanModel
     */
    private fun drawLine(g: Graphics, x1: Int, y1: Int, x2: Int, y2: Int, w: Int = 0) {
        val g2d = g.create() as Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.drawLine(x1 + RADIUS / 2, y1 + RADIUS / 2, x2 + RADIUS / 2, y2 + RADIUS / 2)
        if (w != 0) {
            weightList.add(Weight((x1 + x2 + RADIUS) / 2, (y1 + y2 + RADIUS) / 2, w))//记录权重稍后打印
        }
        g2d.dispose()
    }

    companion object {
        const val PIC_WIDTH = 920
        const val PIC_HEIGHT = 600
        const val RADIUS = 6
        const val COEFFICIENT = 1

        /**
         * @description:由给定邻接矩阵及坐标绘制无向图的面板
         * @return: 含有无向图的面板
         * @author: KanModel
         */
        fun getTreePanel(cost: Array<IntArray> = DataHolder.cost, coor: Array<IntArray> = DataHolder.defaultCoordinateGenerate(), dim: Int = DataHolder.cost.size): TreePanel {
            val treePanel: TreePanel = TreePanel(cost, coor, dim)
            treePanel.border = BorderFactory.createEmptyBorder(2, 5, 2, 5)
            return treePanel
        }

        /**
         * description:获取原图无向图面板
         * @return: 含有原图的面板
         * @author: KanModel
         */
        fun getPicPanel(): JPanel {
            val mainPanel = JPanel()
            mainPanel.background = Color.gray
            mainPanel.preferredSize = Dimension(600, 600)
            mainPanel.border = BorderFactory.createEmptyBorder(0, 0, 0, 0)
            mainPanel.add(getTreePanel(coor = DataHolder.coor))
            return mainPanel
        }
    }

    data class Weight(val x: Int, val y: Int, val w: Int)
    data class Name(val x: Int, val y: Int, val name: String)
}