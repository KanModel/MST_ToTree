package me.kanmodel.sep18.algorithm.gui

import java.awt.Color
import java.awt.Component
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.SwingConstants
import javax.swing.UIManager
import javax.swing.table.TableCellRenderer

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: KanModel
 * Date: 2018-10-13
 * Time: 10:29
 */
/**
 * 表格第一列样式
 * 保持与表头样式一致
 */
class RowHeaderRenderer : TableCellRenderer {

    private val label = JLabel()
    override fun getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
        label.font = font
        label.horizontalAlignment = SwingConstants.CENTER
        label.text = value.toString()
        label.isOpaque = true
        label.foreground = fgc
        label.background = bgc
        return label
    }

    companion object {
        // 获取表头的字体、前景色和背景色，用来将Label伪装成表头的样子
        private val font = UIManager.get("TableHeader.font") as Font
        private val fgc = UIManager.get("TableHeader.foreground") as Color
        private val bgc = UIManager.get("TableHeader.background") as Color
    }
}