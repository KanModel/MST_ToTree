package me.kanmodel.sep18.algorithm.gui


import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.*
import java.awt.RenderingHints
import java.awt.Graphics2D
import javax.swing.*

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-18
 * Time: 20:46
 */

const val RIDUS = 50
const val COEFFICIENT = 1

var count: Int = 1

class TreeFrame(cost: Array<IntArray>? = null, coor: Array<IntArray>? = null, val dim: Int = DataHolder.cost.size) : JFrame("PicToTree") {
    val flowLayout = FlowLayout()
    val mainPanel = JPanel(flowLayout)
    val scrollPane = JScrollPane(mainPanel)
    var count = 0

    init {
        flowLayout.vgap = 4
        flowLayout.hgap = 0

        mainPanel.background = Color.gray
        mainPanel.preferredSize = Dimension(610, (dim - 1) * 604)
        mainPanel.border = BorderFactory.createEmptyBorder(0, 0, 0, 0)
        scrollPane.setBounds(0, 0, 620, 610)
        scrollPane.verticalScrollBar.unitIncrement = 20//���ù��ֹ����ٶ�

        contentPane = scrollPane
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        pack()
        setLocationRelativeTo(null)
        isResizable = false
        setSize(1080, 720)
        setLocation(64, 64)
        iconImage = Toolkit.getDefaultToolkit().getImage("icon_tree.png")
    }

    fun addTreePanel(cost: Array<IntArray>, coor: Array<IntArray>) {
        val treePanel: TreePanel = TreePanel(this, cost, dim = dim)
//        val treePanel: TreePanel = TreePanel(this, cost, coor, dim)
        treePanel.add(JLabel("����${++count}"))
        treePanel.border = BorderFactory.createEmptyBorder(2, 5, 2, 5)
        mainPanel.add(treePanel)
//        this.pack()
    }

    fun addTreePanel(panel: JPanel) {
        mainPanel.add(panel)
    }

    fun setMainSize(w: Int = 600, h: Int = 600){
        mainPanel.preferredSize = Dimension(w, h)
    }
}

class TreePanel(val frame: JFrame?, private val cost: Array<IntArray>, private val coor: Array<IntArray> = DataHolder.defaultCoordinateGenerate(), val dim: Int = DataHolder.cost.size) : JPanel() {
    init {
//        background = Color.
//        setSize(500, 500)
        preferredSize = Dimension(600, 600)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        drawPicture(g!!)
    }

    private fun drawPicture(g: Graphics) {
        for (i in 0 until dim) {
            for (j in i + 1 until dim) {
                if (cost[i][j] != Int.MAX_VALUE) {
                    drawP2P(g, coor[i][0] * COEFFICIENT, coor[i][1] * COEFFICIENT, coor[j][0] * COEFFICIENT, coor[j][1] * COEFFICIENT, cost[i][j], (i + 1).toString(), (j + 1).toString())
                }
            }
        }
    }

    private fun test(g: Graphics) {
        drawP2P(g, 100, 100, 200, 200, 200)
        drawP2P(g, 200, 100, 300, 300, 160)
    }

    private fun drawP2P(g: Graphics, x1: Int, y1: Int, x2: Int, y2: Int, w: Int = 0, firstName: String = "", secondName: String = "") {
        drawLine(g, x1, y1, x2, y2, w)
        drawCircle(g, x1, y1, firstName)
        drawCircle(g, x2, y2, secondName)
    }

    private fun drawCircle(g: Graphics, x: Int = 0, y: Int = 0, name: String = "") {
        val g2d = g.create() as Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.color = Color.pink
        g2d.fillArc(x, y, RIDUS, RIDUS, 0, 360)
        g2d.color = Color.BLACK
        g2d.drawString(name, (x + RIDUS / 2 - 5), (y + RIDUS / 2 + 5))
        g2d.dispose()
    }

    private fun drawLine(g: Graphics, x1: Int, y1: Int, x2: Int, y2: Int, w: Int = 0) {
        val g2d = g.create() as Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25)
        g2d.color = Color.RED
        if (w != 0) {
            g2d.drawString(w.toString(), (x1 + x2 + RIDUS) / 2, (y1 + y2 + RIDUS) / 2)
        }
        g2d.dispose()
    }

}

fun getTreePanel(cost: Array<IntArray> = DataHolder.cost, coor: Array<IntArray> = DataHolder.defaultCoordinateGenerate()): TreePanel {
    val treePanel: TreePanel = TreePanel(null, cost, coor, DataHolder.cost.size)
    treePanel.border = BorderFactory.createEmptyBorder(2, 5, 2, 5)
    return treePanel
}

fun getPicPanel(): JPanel {
    val mainPanel = JPanel()
    mainPanel.background = Color.gray
    mainPanel.preferredSize = Dimension(600, 600)
    mainPanel.border = BorderFactory.createEmptyBorder(0, 0, 0, 0)
    mainPanel.add(getTreePanel())
    return mainPanel
}

fun main(args: Array<String>) {
    val cost = DataHolder.cost
//    val coor = arrayOf(
//            intArrayOf(1, 1),
//            intArrayOf(3, 1),
//            intArrayOf(5, 2),
//            intArrayOf(1, 3),
//            intArrayOf(3, 3),
//            intArrayOf(2, 5)
//    )
    val coor = DataHolder.defaultCoordinateGenerate()
    val frame = TreeFrame()
    frame.addTreePanel(getTreePanel())
    frame.setMainSize()
    frame.isVisible = true
}