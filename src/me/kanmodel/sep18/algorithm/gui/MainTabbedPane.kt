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
        addTab("��ӭ", WelcomePane())
        addTab("�����ڽӾ���", getTablePane())
        addTab("ԭͼ", null)
        addTab("��С���������", null)
        selectedIndex = 0
        addChangeListener {
            println("��ǰѡ�е�ѡ�index: $selectedIndex")
            when (selectedIndex) {
                2 -> setComponentAt(selectedIndex, getTreePanel())
                3 -> setComponentAt(selectedIndex, getTreeFram().scrollPane)
            }
        }
    }
}