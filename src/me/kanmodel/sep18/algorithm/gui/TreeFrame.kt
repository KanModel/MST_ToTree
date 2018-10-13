package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Toolkit
import javax.swing.*

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-10-13
 * Time: 10:43
 */
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
        scrollPane.verticalScrollBar.unitIncrement = 20//设置滚轮滚动速度

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
        treePanel.add(JLabel("步骤${++count}"))
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