package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.getTreeFram
import javax.swing.JTabbedPane

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:20
 */
class MainTabbedPane : JTabbedPane() {
    init {
        addTab("欢迎", WelcomePane())
        addTab("矩阵邻接矩阵", getTablePane())
        addTab("原图", null)
        addTab("最小生成树结果", null)
        selectedIndex = 0
        addChangeListener {
            println("当前选中的选项卡index: $selectedIndex")
            when (selectedIndex) {
                2 -> setComponentAt(selectedIndex, getTreePanel())
                3 -> setComponentAt(selectedIndex, getTreeFram().scrollPane)
            }
        }
    }
}