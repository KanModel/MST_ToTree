package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.algorithm.Kruskal
import me.kanmodel.sep18.algorithm.algorithm.Prim
import me.kanmodel.sep18.algorithm.gui.CoorTable.Companion.getCoorTablePane
import me.kanmodel.sep18.algorithm.gui.PictureADMTable.Companion.getADMTablePane
import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.getPicPanel
import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.DataHolder.PRIM
import me.kanmodel.sep18.algorithm.util.Log
import java.awt.Font
import javax.swing.JTabbedPane

/**
 * Description: ѡ����
 * @author: KanModel
 * Date: 2018-09-25
 * Time: 14:20
 */
class MainTabbedPanel : JTabbedPane() {

    init {
        font = Font(null, Font.PLAIN, 18)
        addTab("��ӭ", null, WelcomePanel(), "����ά��/ѡ���㷨")
        addTab("�ڽӾ���", null, null, "��ʾ/�༭��ǰ�ڽӾ���")
        addTab("�������", null, null, "��ʾ/�༭��ǰ�����ʾ����")
        addTab("ԭͼ", null, null, "���ݵ�ǰ�ڽӾ��󻭳���Ȩ����ͼ")
        addTab("��С���������", null, null, "��ʾ��ǰ�ڽӾ����µ���С��������ϸ����")
        selectedIndex = 0
        addChangeListener {
            Log.i("��ǰѡ�е�ѡ�index: $selectedIndex")
            when (selectedIndex) {
                TABLE -> setComponentAt(TABLE, getADMTablePane())//�ڽӾ�����
                COORDINATE_TABLE -> setComponentAt(COORDINATE_TABLE, getCoorTablePane())//�ڽӾ�����
                SOURCE_PICTURE -> setComponentAt(SOURCE_PICTURE, getPicPanel())//ԭͼ
                STEP_PICTURE -> {
                    if (DataHolder.algorithmSelect == PRIM) {
                        setComponentAt(STEP_PICTURE, Prim.getTreeStepPanel().scrollPanel)//����ͼ
                    } else {
                        setComponentAt(STEP_PICTURE, Kruskal.getTreeStepPanel().scrollPanel)//����ͼ
                    }
                }
            }
        }
    }

    companion object {
        const val TABLE = 1
        const val COORDINATE_TABLE = 2
        const val SOURCE_PICTURE = 3
        const val STEP_PICTURE = 4
    }
}