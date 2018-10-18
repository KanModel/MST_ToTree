package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.PIC_WIDTH
import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.*
import javax.swing.*

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-26
 * Time: 23:30
 */
class TreeStepPanel(private val dim: Int = DataHolder.cost.size) {
    private val flowLayout = FlowLayout()
    private val mainPanel = JPanel(flowLayout)
    val scrollPanel = JScrollPane(mainPanel)
    var count = 0

    init {

        flowLayout.vgap = 4
        flowLayout.hgap = 0

        mainPanel.background = Color.gray
        mainPanel.preferredSize = Dimension(610, (dim - 1) * 604 + 40)
        mainPanel.border = BorderFactory.createEmptyBorder(0, 0, 0, 0)
        scrollPanel.setBounds(0, 0, 620, 610)
        scrollPanel.verticalScrollBar.unitIncrement = 20//设置滚轮滚动速度

        scrollPanel.setSize(1080, 720)
        scrollPanel.setLocation(64, 64)
    }

    fun addTreePanel(cost: Array<IntArray>, coor: Array<IntArray> = DataHolder.coor) {
        val treePanel: TreePanel = TreePanel(null, cost, coor = coor, dim = dim)
        val stepLabel = JLabel("步骤${++count}")
        stepLabel.font = Font(null, Font.PLAIN, 14)
        treePanel.add(stepLabel)
        treePanel.border = BorderFactory.createEmptyBorder(2, 5, 2, 5)
        mainPanel.add(treePanel)
    }

    fun addResultPanel(minCost: Int) {
        val panel = JPanel()
        panel .preferredSize = Dimension(PIC_WIDTH, 30)
        panel.add(JLabel("最短路径长度: $minCost"))
//                preferredSize = Dimension(PIC_WIDTH, PIC_HEIGHT)
        mainPanel.add(panel)
    }
}