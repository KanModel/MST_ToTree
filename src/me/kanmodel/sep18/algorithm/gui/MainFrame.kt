package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.Dimension
import java.awt.Toolkit
import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-09-25
 * Time: 14:20
 */
class MainFrame : JFrame() {
    init {
        size = Dimension(1080, 720)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        setLocationRelativeTo(null)
        addWindowListener(TableWindowListener(DataHolder.cost.size))
        title = "ToTree"
        iconImage = Toolkit.getDefaultToolkit().getImage("images/icon_tree.png")//todo jar
        contentPane = MainTabbedPanel()
    }
}