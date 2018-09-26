package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.getTreeFrame
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
        addTab("矩阵邻接矩阵", null)
        addTab("原图", null)
        addTab("最小生成树结果", null)
        selectedIndex = 0
        addChangeListener {
            println("当前选中的选项卡index: $selectedIndex")
            when (selectedIndex) {
                1 -> setComponentAt(1, getTablePane())
                2 -> setComponentAt(2, getPicPanel())
                3 -> setComponentAt(3, getTreeFrame().scrollPane)
            }
        }
    }
}