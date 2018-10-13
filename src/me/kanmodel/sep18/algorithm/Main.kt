package me.kanmodel.sep18.algorithm

import me.kanmodel.sep18.algorithm.gui.MainFrame
import javax.swing.SwingUtilities

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:07
 */
object Main{
    val isDebug = true

    @JvmStatic
    fun main(args: Array<String>){
        SwingUtilities.invokeLater {
            MainFrame().isVisible = true
        }
    }
}
