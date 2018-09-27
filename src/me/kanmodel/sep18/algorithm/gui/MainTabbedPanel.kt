package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.getTreeFrame
import me.kanmodel.sep18.algorithm.getTreeStepPanel
import javax.swing.JTabbedPane

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:20
 */
class MainTabbedPanel : JTabbedPane() {
    init {
        addTab("��ӭ", WelcomePanel())
        addTab("�����ڽӾ���", null)
        addTab("ԭͼ", null)
        addTab("��С���������", null)
        selectedIndex = 0
        addChangeListener {
            println("��ǰѡ�е�ѡ�index: $selectedIndex")
            when (selectedIndex) {
                1 -> setComponentAt(1, getTablePane())
                2 -> setComponentAt(2, getPicPanel())
//                3 -> setComponentAt(3, getTreeFrame().scrollPane)
                3 -> setComponentAt(3, getTreeStepPanel().scrollPanel)
            }
        }
    }
}