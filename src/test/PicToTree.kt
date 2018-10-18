package test


import me.kanmodel.sep18.algorithm.gui.TreeFrame
import me.kanmodel.sep18.algorithm.gui.TreePanel.Companion.getTreePanel

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
        val cost = arrayOf(
                intArrayOf(0, 3),
                intArrayOf(3, 0)
        )
        val coor = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(592, 592)
        )
        val frame = TreeFrame()
        frame.addTreePanel(getTreePanel(coor = coor, cost = cost, dim = 2))
        frame.setMainSize()
        frame.isVisible = true
    }
}
