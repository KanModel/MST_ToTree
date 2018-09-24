package me.kanmodel.sep18.algorithm


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

var count: Int = 1

class TreeFrame(cost: Array<IntArray>? = null, coor: Array<IntArray>? = null, val dim: Int = 6) : JFrame("PicToTree") {
    val mainPanel = JPanel(FlowLayout())

    init {
        mainPanel.background = Color.gray
//        val text = JTextArea()
//        val text2 = JScrollPane(text)
//        mainPanel.add(text2)
//        val treePanel: TreePanel = TreePanel(this, cost!!, coor!!, dim)
//        mainPanel.add(treePanel)

        contentPane = mainPanel
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        pack()
        setLocationRelativeTo(null)
        isResizable = true
        setSize(1000, 800)
        setLocation(108, 72)
        iconImage = Toolkit.getDefaultToolkit().getImage("icon_tree.png")
    }

    fun addTreePanel(cost: Array<IntArray>, coor: Array<IntArray>) {
        val treePanel: TreePanel = TreePanel(this, cost, coor, dim)
        mainPanel.add(treePanel)
        this.pack()
    }
}

fun copyArray(source: Array<IntArray>): Array<IntArray> {
    val clone = Array(source.size){IntArray(source.size)}
    for (i in 0 until source.size) {
        for (j in 0 until source[i].size) {
            clone[i][j] = source[i][j]
        }
    }
    return clone
}

class TreePanel(val frame: JFrame, private val cost: Array<IntArray>, private val coor: Array<IntArray>, val dim: Int = 6) : JPanel() {
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
                    drawP2P(g, coor[i][0] * 100, coor[i][1] * 100, coor[j][0] * 100, coor[j][1] * 100, cost[i][j], (i + 1).toString(), (j + 1).toString())
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

fun main(args: Array<String>) {
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
    val frame = TreeFrame(cost, coor)
    frame.isVisible = true
/*    EventQueue.invokeLater {
        // �������ڶ���
//        val frame = Main.MyFrame()
        val frame = TreeFrame(cost, coor)
//        frame.setSize(1080, 720)
        // ��ʾ����
        frame.isVisible = true
    }*/
}
