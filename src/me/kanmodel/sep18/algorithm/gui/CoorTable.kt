package me.kanmodel.sep18.algorithm.gui

import me.kanmodel.sep18.algorithm.util.DataHolder
import java.awt.BorderLayout
import java.util.regex.Pattern
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.table.AbstractTableModel

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kgdwhsk
 * Date: 2018-10-17
 * Time: 19:04
 * 4列 第一列序号 第二列名字 三，四列x，y坐标
 */
class CoorTable : AbstractTableModel() {
    override fun getRowCount(): Int {
        return DataHolder.coor.size
    }

    override fun getColumnCount(): Int {
        return 4
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return if (columnIndex in 2..3) {
            DataHolder.coor[rowIndex][columnIndex - 2]
        } else if (columnIndex == 0) {
            rowIndex + 1
        } else if (columnIndex == 1) {
            DataHolder.names[rowIndex]
        } else {
            ""
        }
    }

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        if (columnIndex in 2..3) {
            val regex = "\\d*|∞|max|m"
            val p = Pattern.compile(regex)
            if (!p.matcher(aValue.toString()).matches()) {
                println("不符合格式取消改变值")
                return
            }
            DataHolder.coor[rowIndex][columnIndex - 2] = aValue.toString().toInt()
            DataHolder.saveCoor(DataHolder.coor)
        }
        fireTableCellUpdated(rowIndex, columnIndex)
    }

    override fun getColumnName(column: Int): String {
        return columnNames[column]
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        return columnIndex in 2..3
    }

    companion object {
        var columnNames = arrayOf("编号", "名称", "X", "Y")

        fun getCoorTablePane(): JPanel {
            val panel = JPanel(BorderLayout())

            val table = JTable(CoorTable())

            table.columnModel.getColumn(0).cellRenderer = RowHeaderRenderer()
            table.rowHeight = 20
            for (i in 0 until 4) {
                table.columnModel.getColumn(i).preferredWidth = 40
            }

            table.tableHeader.resizingAllowed = true
            val tableModel = table.model
//            tableModel.addTableModelListener(ADMTableListener(tableModel))

            panel.add(table.tableHeader, BorderLayout.NORTH)
            panel.add(table, BorderLayout.CENTER)

            return panel
        }
    }
}