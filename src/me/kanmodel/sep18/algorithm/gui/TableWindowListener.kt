package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import me.kanmodel.sep18.algorithm.util.DataHolder.print2D
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-23
 * Time: 10:43
 */


/**
 * 窗口监听类
 * 当窗口关闭时保存数据
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
    }

    override fun windowClosed(e: WindowEvent?) {}

    override fun windowActivated(e: WindowEvent?) {}

    override fun windowDeactivated(e: WindowEvent?) {}

    override fun windowOpened(e: WindowEvent?) {}

    override fun windowIconified(e: WindowEvent?) {}
}