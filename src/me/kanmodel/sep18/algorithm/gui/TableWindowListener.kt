package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
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
 * 窗口监听类
 * 当窗口关闭时保存数据
 */
class TableWindowListener(val dim: Int = 1): WindowListener{
    override fun windowDeiconified(e: WindowEvent?) {
    }

    override fun windowClosing(e: WindowEvent?) {
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