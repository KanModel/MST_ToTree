package me.kanmodel.sep18.algorithm.gui


import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.getTreePanel
import me.kanmodel.sep18.algorithm.util.DataHolder

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-18
 * Time: 20:46
 */
object PicToTree {

    @JvmStatic
    fun main(args: Array<String>) {
        val cost = DataHolder.cost
        val coor = DataHolder.coor
        val frame = TreeFrame()
        frame.addTreePanel(getTreePanel(coor = coor))
        frame.setMainSize()
        frame.isVisible = true
    }
}
