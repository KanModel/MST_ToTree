package me.kanmodel.sep18.algorithm

import me.kanmodel.sep18.algorithm.gui.MainFrame
import javax.swing.SwingUtilities

/**
 * Description:程序主入口类
 * @author: KanModel
 * Date: 2018-09-25
 * Time: 14:07
 */
class Main{
    companion object {
        const val isDebug = true

        @JvmStatic
        fun main(args: Array<String>){
            SwingUtilities.invokeLater {
                MainFrame().isVisible = true
            }
        }
    }
}
