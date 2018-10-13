package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.Prim.Companion.getTreeStepPanel
import me.kanmodel.sep18.algorithm.gui.PictureTable.Companion.getTablePane
import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.getPicPanel
import me.kanmodel.sep18.algorithm.util.Log
import java.awt.Font
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
        font = Font(null, Font.PLAIN, 18)
//        addTab("��ӭ", WelcomePanel())
        addTab("��ӭ", null, WelcomePanel(), "������")
        addTab("�����ڽӾ���", null, null, "��ʾ/�༭��ǰ�ڽӾ���")
        addTab("ԭͼ", null, null, "���ݵ�ǰ�ڽӾ��󻭳���Ȩ����ͼ")
        addTab("��С���������", null, null, "��ʾ��ǰ�ڽӾ����µ���С��������ϸ����")
        selectedIndex = 0
        addChangeListener {
            Log.i("��ǰѡ�е�ѡ�index: $selectedIndex")
            when (selectedIndex) {
                1 -> setComponentAt(1, getTablePane())//�ڽӾ�����
                2 -> setComponentAt(2, getPicPanel())//ԭͼ
                3 -> setComponentAt(3, getTreeStepPanel().scrollPanel)//����ͼ
            }
        }
    }
}