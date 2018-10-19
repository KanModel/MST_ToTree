package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.DataHolder.print2D
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: KanModel
 * Date: 2018-09-23
 * Time: 10:43
 */


/**
 * ���ڼ�����
 * �����ڹر�ʱ��������
 */
class TableWindowListener(val dim: Int = 1): WindowListener{
    override fun windowDeiconified(e: WindowEvent?) {
    }

    override fun windowClosing(e: WindowEvent?) {
//        val data = Array(dim) { IntArray(dim) { 0 } }
//        for (i in 0 until dim) {
//            for (j in 0 until dim) {
//                data[i][j] = rowData[i][j + 1]
//            }
//        }
//        print2D(data)
        DataHolder.save(DataHolder.cost)
        DataHolder.saveCoor(DataHolder.coor)
        DataHolder.saveNames(DataHolder.names)
    }

    override fun windowClosed(e: WindowEvent?) {}

    override fun windowActivated(e: WindowEvent?) {}

    override fun windowDeactivated(e: WindowEvent?) {}

    override fun windowOpened(e: WindowEvent?) {}

    override fun windowIconified(e: WindowEvent?) {}
}